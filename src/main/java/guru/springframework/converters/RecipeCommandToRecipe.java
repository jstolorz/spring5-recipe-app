package guru.springframework.converters;


import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand,Recipe> {

    private final CategoryCommandToCategory toCategory;
    private final IngredientCommandToIngredient toIngredient;
    private final NotesCommandToNotes toNotes;

    public RecipeCommandToRecipe(CategoryCommandToCategory toCategory, IngredientCommandToIngredient toIngredient, NotesCommandToNotes toNotes) {
        this.toCategory = toCategory;
        this.toIngredient = toIngredient;
        this.toNotes = toNotes;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null) {
            return null;
        }

        final Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setDescription(source.getDescription());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setCookTime(source.getCookTime());
        recipe.setServings(source.getServings());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());
        recipe.setDirections(source.getDirections());

        if(source.getIngredients() != null && source.getIngredients().size() > 0){
            source.getIngredients().
             forEach(ingredient -> recipe.getIngredients().add(toIngredient.convert(ingredient)));
        }

        recipe.setDifficulty(source.getDifficulty());
        recipe.setNotes(toNotes.convert(source.getNotes()));

        if(source.getCategories() != null && source.getCategories().size() > 0){
           source.getCategories().
            forEach(category -> recipe.getCategories().add(toCategory.convert(category)));
        }

        return recipe;
    }
}
