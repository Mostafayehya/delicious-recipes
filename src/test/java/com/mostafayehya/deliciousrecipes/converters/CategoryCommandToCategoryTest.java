package com.mostafayehya.deliciousrecipes.converters;

import com.mostafayehya.deliciousrecipes.comands.CategoryCommand;
import com.mostafayehya.deliciousrecipes.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

    Long ID = 1L;
    String DESCRIPTION = "description";

    CategoryCommandToCategory converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryCommandToCategory();
    }

    @Test
    public void testNullCategoryCommand(){

        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyCategoryCommand(){

        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    public void convert() {

        // give
        CategoryCommand cc = new CategoryCommand();
        cc.setId(ID);
        cc.setDescription(DESCRIPTION);

        // when
        Category returnedCategory = converter.convert(cc);

        // then
        assertEquals(ID,returnedCategory.getId());
        assertEquals(DESCRIPTION,returnedCategory.getDescription());
    }
}