package com.mostafayehya.deliciousrecipes.converters;

import com.mostafayehya.deliciousrecipes.comands.NoteCommand;
import com.mostafayehya.deliciousrecipes.domain.Note;
import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NoteToNoteCommand implements Converter<Note,NoteCommand> {

    @Synchronized
    @Nullable
    @Override
    public NoteCommand convert(Note source) {
        if (source == null) {
            return null;
        }
        final NoteCommand noteCommand = new NoteCommand();
        noteCommand.setId(source.getId());
        noteCommand.setRecipeNotes(source.getRecipeNotes());
        return noteCommand;
    }
}
