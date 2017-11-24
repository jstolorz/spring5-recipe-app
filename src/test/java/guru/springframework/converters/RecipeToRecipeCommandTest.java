package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RecipeToRecipeCommandTest {

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

    RecipeToRecipeCommand command;

    @Before
    public void setUp() throws Exception {
        command = new RecipeToRecipeCommand(new CategoryToCategoryCommand(), new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new NotesToNotesCommand());
    }

    @Test
    public void convert() throws Exception {

        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setCookTime(COOK_TIME);
        recipe.setPrepTime(PREP_TIME);
        recipe.setDescription(DESCRIPTION);
        recipe.setDifficulty(DIFFICOULTY);
        recipe.setDirections(DIRECTION);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);

        Notes notes = new Notes();
        notes.setId(NOT_ID);

        recipe.setNotes(notes);

        Category category = new Category();
        category.setId(CAT_ID_1);

        Category category2 = new Category();
        category2.setId(CAT_ID_2);

        recipe.getCategories().add(category);
        recipe.getCategories().add(category2);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(ING_ID_1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(ING_ID_2);

        recipe.getIngredients().add(ingredient);
        recipe.getIngredients().add(ingredient2);

        RecipeCommand recipeCommand = command.convert(recipe);

        assertNotNull(command);
        assertEquals(RECIPE_ID, recipeCommand.getId());
        assertEquals(COOK_TIME, recipeCommand.getCookTime());
        assertEquals(PREP_TIME, recipeCommand.getPrepTime());
        assertEquals(DESCRIPTION, recipeCommand.getDescription());
        assertEquals(DIFFICOULTY, recipeCommand.getDifficulty());
        assertEquals(DIRECTION, recipeCommand.getDirections());
        assertEquals(SERVINGS, recipeCommand.getServings());
        assertEquals(SOURCE, recipeCommand.getSource());
        assertEquals(URL, recipeCommand.getUrl());
        assertEquals(NOT_ID, recipeCommand.getNotes().getId());
        assertEquals(2, recipeCommand.getCategories().size());
        assertEquals(2, recipeCommand.getIngredients().size());

    }

}