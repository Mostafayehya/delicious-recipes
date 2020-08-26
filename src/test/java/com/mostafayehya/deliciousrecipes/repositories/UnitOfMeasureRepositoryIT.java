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
    public void findByName() {

        Optional<UnitOfMeasure> optionalUnitOfMeasure = unitOfMeasureRepository.findByName("Teaspoon");

        assertEquals("Teaspoon",optionalUnitOfMeasure.get().getName());
    }
    @Test
    public void findByNameCup() {

        Optional<UnitOfMeasure> optionalUnitOfMeasure = unitOfMeasureRepository.findByName("Cup");

        assertEquals("Cup",optionalUnitOfMeasure.get().getName());
    }
}