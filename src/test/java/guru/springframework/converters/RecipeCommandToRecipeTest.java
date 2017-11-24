package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.NotesCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

    private static final Long RECIPE_ID = new Long(1L);
    private static final String DESCRIPTION = "description";
    private static final Integer PREP_TIME = new Integer(5);
    private static final Integer COOK_TIME = new Integer(7);
    private static final Integer SERVINGS = new Integer(3);
    private static final String SOURCE = "source";
    private static final String URL = "localhost";
    private static final String DIRECTION = "directions";
    private static final Long ING_ID_1 = new Long(1L);
    private static final Long ING_ID_2 = new Long(2L);
    private static final Difficulty DIFFICOULTY = Difficulty.HARD;
    private static final Long CAT_ID_1 = new Long(1L);
    private static final Long CAT_ID_2 = new Long(3L);
    private static final Long NOT_ID = new Long(1L);

    RecipeCommandToRecipe command;


    @Before
    public void setUp() throws Exception {
        command = new RecipeCommandToRecipe(new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes());
    }

    @Test
    public void convert() throws Exception {

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(RECIPE_ID);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);
        recipeCommand.setDirections(DIRECTION);

        IngredientCommand ingredientCommand1 = new IngredientCommand();
        ingredientCommand1.setId(ING_ID_1);

        IngredientCommand ingredientCommand2 = new IngredientCommand();
        ingredientCommand1.setId(ING_ID_2);

        recipeCommand.getIngredients().add(ingredientCommand1);
        recipeCommand.getIngredients().add(ingredientCommand2);

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOT_ID);

        recipeCommand.setNotes(notesCommand);
        recipeCommand.setDifficulty(DIFFICOULTY);


        CategoryCommand categoryCommand1 = new CategoryCommand();
        categoryCommand1.setId(CAT_ID_1);

        CategoryCommand categoryCommand2 = new CategoryCommand();
        categoryCommand2.setId(CAT_ID_2);

        recipeCommand.getCategories().add(categoryCommand1);
        recipeCommand.getCategories().add(categoryCommand2);

        Recipe recipe = command.convert(recipeCommand);

        assertNull(command.convert(null));
        assertNotNull(recipe);

        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(DIRECTION, recipe.getDirections());
        assertEquals(2, recipe.getIngredients().size());
        assertEquals(DIFFICOULTY, recipe.getDifficulty());
        assertEquals(NOT_ID, recipe.getNotes().getId());
        assertEquals(2,recipe.getCategories().size());

    }

}