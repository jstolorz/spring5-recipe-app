package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

    private static final Long ID_VALUE = new Long(1L);
    private static final String RECIPE_NOTES = "Recipe Notes";

    NotesCommandToNotes command;

    @Before
    public void setUp() throws Exception {
        command = new NotesCommandToNotes();
    }

    @Test
    public void testEmptyObject() throws Exception{
        assertNotNull(command.convert(new NotesCommand()));
    }

    @Test
    public void testNullObject() throws Exception{
        assertNull(command.convert(null));
    }

    @Test
    public void convert() throws Exception {

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(ID_VALUE);
        notesCommand.setRecipeNotes(RECIPE_NOTES);

        Notes notes = command.convert(notesCommand);

        assertNotNull(notes);
        assertNull(command.convert(null));
        assertEquals(ID_VALUE, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }

}