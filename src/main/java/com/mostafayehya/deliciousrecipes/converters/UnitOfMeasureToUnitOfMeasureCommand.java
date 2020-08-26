package com.mostafayehya.deliciousrecipes.converters;

import com.mostafayehya.deliciousrecipes.comands.UnitOfMeasureCommand;
import com.mostafayehya.deliciousrecipes.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure source) {
        if (source == null)
            return null;

        UnitOfMeasureCommand uomc = new UnitOfMeasureCommand();
        uomc.setId(source.getId());
        uomc.setName(source.getName());

        return uomc;
    }
}
