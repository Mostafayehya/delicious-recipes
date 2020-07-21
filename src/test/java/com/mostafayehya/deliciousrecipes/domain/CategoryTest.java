package com.mostafayehya.deliciousrecipes.domain;

import com.mostafayehya.deliciousrecipes.repositories.CategoriesRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    Category category;

    @Before
    public void setUp() throws Exception {
        category = new Category();
    }

    @Test
    public void getId() {
        Long id = 4L;
        category.setId(id);
        assertEquals(id,category.getId());
    }

    @Test
    public void getDescription() {
    }

    @Test
    public void getRecipes() {
    }
}