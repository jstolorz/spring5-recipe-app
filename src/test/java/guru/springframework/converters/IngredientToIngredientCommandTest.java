package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {

    private static final Long ID_VALUE = new Long(1L);
    private static final String DESCRIPTION = "Description";
    private static final BigDecimal AMOUNT = new BigDecimal("1");
    private static final Long UOM_ID = new Long(2L);


    IngredientToIngredientCommand command;

    @Before
    public void setUp() throws Exception {
        command = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNullObject() throws Exception{
        assertNull(command.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception{
        assertNotNull(command.convert(new Ingredient()));
    }

    @Test
    public void convert() throws Exception {

        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);

        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(UOM_ID);

        ingredient.setUnitOfMeasure(unitOfMeasure);

        IngredientCommand ingredientCommand = command.convert(ingredient);

        assertNotNull(ingredientCommand);
        assertNull(command.convert(null));

        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(UOM_ID, ingredientCommand.getUom().getId());

    }

    @Test
    public void testNullUomConvert() throws Exception{

        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);

        IngredientCommand ingredientCommand = command.convert(ingredient);

        assertNotNull(ingredientCommand);
        assertNull(command.convert(null));

        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertNull(ingredientCommand.getUom());

    }

}