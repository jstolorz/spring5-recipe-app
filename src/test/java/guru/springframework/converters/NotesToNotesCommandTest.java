package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {

    private static final Long ID_VALUE = new Long(1L);
    private static final String RECIPE_NOTES = "Recipe";

    NotesToNotesCommand command;

    @Before
    public void setUp() throws Exception {
        command = new NotesToNotesCommand();
    }

    @Test
    public void convert() throws Exception {

        Notes notes = new Notes();
        notes.setId(ID_VALUE);
        notes.setRecipeNotes(RECIPE_NOTES);

        NotesCommand notesCommand = command.convert(notes);

        assertNull(command.convert(null));
        assertNotNull(notesCommand);

        assertEquals(ID_VALUE, notesCommand.getId());
        assertEquals(RECIPE_NOTES, notesCommand.getRecipeNotes());

    }

}