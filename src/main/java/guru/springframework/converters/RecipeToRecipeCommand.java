package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand>{

    private final CategoryToCategoryCommand toCategoryCommand;
    private final IngredientToIngredientCommand toIngredientCommand;
    private final NotesToNotesCommand toNotesCommand;

    public RecipeToRecipeCommand(CategoryToCategoryCommand toCategoryCommand, IngredientToIngredientCommand toIngredientCommand, NotesToNotesCommand toNotesCommand) {
        this.toCategoryCommand = toCategoryCommand;
        this.toIngredientCommand = toIngredientCommand;
        this.toNotesCommand = toNotesCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {

        if(source == null) {
            return null;
        }

        final RecipeCommand recipeCommand = new RecipeCommand();

        recipeCommand.setId(source.getId());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setNotes(toNotesCommand.convert(source.getNotes()));


        if(source.getIngredients() != null && source.getIngredients().size() > 0){
            source.getIngredients().
                    forEach(ingredient -> recipeCommand.getIngredients().add(toIngredientCommand.convert(ingredient)));
        }

        if(source.getCategories() != null && source.getCategories().size() > 0){
            source.getCategories().
                    forEach(category -> recipeCommand.getCategories().add(toCategoryCommand.convert(category)));
        }


        return recipeCommand;
    }
}
