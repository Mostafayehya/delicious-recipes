package com.mostafayehya.deliciousrecipes.repositories;

import com.mostafayehya.deliciousrecipes.domain.UnitOfMeasure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Test
    public void findByUom() {

        Optional<UnitOfMeasure> optionalUnitOfMeasure = unitOfMeasureRepository.findByUom("Teaspoon");

        assertEquals("Teaspoon",optionalUnitOfMeasure.get().getUom());
    }
    @Test
    public void findByUomCup() {

        Optional<UnitOfMeasure> optionalUnitOfMeasure = unitOfMeasureRepository.findByUom("Cup");

        assertEquals("Cup",optionalUnitOfMeasure.get().getUom());
    }
}