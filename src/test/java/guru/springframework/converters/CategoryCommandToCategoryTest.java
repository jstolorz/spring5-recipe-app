package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

    public static final String DESCRIPTION = "description";
    public static final Long LONG_VALUE = new Long(1L);

    CategoryCommandToCategory command;

    @Before
    public void setUp() throws Exception {
        command = new CategoryCommandToCategory();
    }

    @Test
    public void testNullObject() throws Exception{
        assertNull(command.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception{
        assertNotNull(command.convert(new CategoryCommand()));
    }

    @Test
    public void convert() throws Exception {

        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(LONG_VALUE);
        categoryCommand.setDescription(DESCRIPTION);

        Category category = command.convert(categoryCommand);

        assertNotNull(category);
        assertEquals(LONG_VALUE, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }

}