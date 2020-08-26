package com.mostafayehya.deliciousrecipes.services;

import com.mostafayehya.deliciousrecipes.comands.UnitOfMeasureCommand;
import com.mostafayehya.deliciousrecipes.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.mostafayehya.deliciousrecipes.domain.UnitOfMeasure;
import com.mostafayehya.deliciousrecipes.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UnitOfMeasureServiceImplTest {

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    UnitOfMeasureToUnitOfMeasureCommand converter = new UnitOfMeasureToUnitOfMeasureCommand();

    UnitOfMeasureServiceImpl unitOfMeasureService;

    @BeforeEach
    void setUp() {

        unitOfMeasureService = new UnitOfMeasureServiceImpl(unitOfMeasureRepository,converter);
    }

    @Test
    void listAllUoms() throws Exception{

        // given
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId(1L);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom1.setId(2L);

        unitOfMeasures.add(uom1);
        unitOfMeasures.add(uom2);

        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);

        // when
        Set<UnitOfMeasureCommand> returendUnitOfMeasures =unitOfMeasureService.listAllUoms();

        // then

        assertEquals(2,returendUnitOfMeasures.size());
        verify(unitOfMeasureRepository,times(1)).findAll();
    }
}