package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {

    public static final String DESCRIPTION = "description";
    public static final Long LONG_VALUE = new Long(1L);

    CategoryToCategoryCommand category;

    @Before
    public void setUp() throws Exception {
        category = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullObject() throws Exception{
        assertNull(category.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception{
        assertNotNull(category.convert(new Category()));
    }

    @Test
    public void convert() throws Exception {

        Category c = new Category();
        c.setId(LONG_VALUE);
        c.setDescription(DESCRIPTION);

        CategoryCommand categoryCommand = category.convert(c);

        assertNotNull(categoryCommand);
        assertEquals(LONG_VALUE, categoryCommand.getId());
        assertEquals(DESCRIPTION,categoryCommand.getDescription());
    }

}