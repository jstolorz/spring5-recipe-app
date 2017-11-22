package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe toRecipe;
    private final RecipeToRecipeCommand toRecipeCommand;


    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe toRecipe, RecipeToRecipeCommand toRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.toRecipe = toRecipe;
        this.toRecipeCommand = toRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipes = new HashSet<>();


        log.info("[ LOMBOK KURNA] ");
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);

        return recipes;
    }

    @Override
    public Recipe findById(Long l) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(l);

        if (!recipeOptional.isPresent()){
            throw new RuntimeException("Recipe not found!");
        }

        return recipeOptional.get();

    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {

        Recipe detacheRecipe = toRecipe.convert(recipeCommand);

        Recipe saveRecipe = recipeRepository.save(detacheRecipe);
        log.debug("Saved Recipe Id: " + saveRecipe.getId());

        return toRecipeCommand.convert(saveRecipe);
    }
}
