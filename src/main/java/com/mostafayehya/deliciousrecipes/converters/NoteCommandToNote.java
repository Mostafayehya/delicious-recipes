package com.mostafayehya.deliciousrecipes.converters;

import com.mostafayehya.deliciousrecipes.comands.NoteCommand;
import com.mostafayehya.deliciousrecipes.domain.Note;
import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NoteCommandToNote implements Converter<NoteCommand, Note> {

    @Synchronized
    @Nullable
    @Override
    public Note convert(NoteCommand source) {
        if (source == null) {
            return null;
        }
        final Note notes = new Note();
        notes.setId(source.getId());
        notes.setRecipeNotes(source.getRecipeNotes());
        return notes;
    }
}
