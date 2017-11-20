package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {


    public static final String DESCRIPTION = "description";
    public static final Long LONG_VALUE = new Long(1L);

    UnitOfMeasureCommandToUnitOfMeasure convert;

    @Before
    public void setUp() throws Exception {
        convert = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void testNullPrameter() throws Exception{
        assertNull(convert.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception{
        assertNotNull(convert.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void convert() throws Exception {
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();

        command.setId(LONG_VALUE);
        command.setDescription(DESCRIPTION);

        UnitOfMeasure unitOfMeasure = convert.convert(command);

        assertNotNull(unitOfMeasure);
        assertEquals(LONG_VALUE, unitOfMeasure.getId());
        assertEquals(DESCRIPTION, unitOfMeasure.getDescription());
    }

}