package com.mostafayehya.deliciousrecipes.services;

import com.mostafayehya.deliciousrecipes.converters.RecipeCommandToRecipe;
import com.mostafayehya.deliciousrecipes.converters.RecipeToRecipeCommand;
import com.mostafayehya.deliciousrecipes.domain.Recipe;
import com.mostafayehya.deliciousrecipes.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    private RecipeServiceImpl recipeServiceImpl;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeServiceImpl = new RecipeServiceImpl(recipeRepository, recipeToRecipeCommand, recipeCommandToRecipe);
    }

    @Test
    void getRecipes() {

        Recipe recipe = new Recipe();
        HashSet recipes = new HashSet();
        recipes.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipes);

        assertEquals(recipeServiceImpl.getRecipes().size(),1);

        // Good way to verify the interactions between objects
        verify(recipeRepository,times(1)).findAll();
    }

    @Test
    void getRecipeByIdTest() throws Exception{

        // given
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> optionalRecipe = Optional.of(recipe);

        // when
        when(recipeRepository.findById(any())).thenReturn(optionalRecipe);
        Recipe recipeReturend = recipeServiceImpl.findById(1L);

        // then
        assertNotNull(recipeReturend);
        verify(recipeRepository,times(1)).findById(anyLong());
    }

    @Test
    public void deleteRecipeByIdTest() throws Exception {

        // given
        Long id = 2L;

        // when
        recipeServiceImpl.deleteById(id);

        // then
        verify(recipeRepository,times(1)).deleteById(id);

    }
}