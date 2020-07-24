package com.mostafayehya.deliciousrecipes.converters;

import com.mostafayehya.deliciousrecipes.comands.NoteCommand;
import com.mostafayehya.deliciousrecipes.domain.Note;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NoteToNoteCommandTest {

    public static final Long ID_VALUE = 1L;

    public static final String RECIPE_NOTES = "Notes";

    NoteToNoteCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new NoteToNoteCommand();
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }


    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Note()));
    }

    @Test
    public void convert() throws Exception {

        //given
        Note note = new Note();
        note.setId(ID_VALUE);
        note.setRecipeNotes(RECIPE_NOTES);

        //when
        NoteCommand notes = converter.convert(note);

        //then
        assertNotNull(notes);
        assertEquals(ID_VALUE, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());


    }
}