package com.mostafayehya.deliciousrecipes.services;

import com.mostafayehya.deliciousrecipes.comands.IngredientCommand;
import com.mostafayehya.deliciousrecipes.converters.IngredientToIngredientCommand;
import com.mostafayehya.deliciousrecipes.domain.Ingredient;
import com.mostafayehya.deliciousrecipes.domain.Recipe;
import com.mostafayehya.deliciousrecipes.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    IngredientToIngredientCommand converter;

    @InjectMocks
    IngredientServiceImpl ingredientService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByRecipeIdAndIngredientID() throws Exception {

        // given
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        IngredientCommand ic = new IngredientCommand();
        ic.setId(3L);
        ic.setRecipeId(1L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);


        Optional<Recipe> optionalRecipe = Optional.of(recipe);

        // when
        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);
        when(converter.convert(any())).thenReturn(ic);

        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientID(1L, 3L);

        // then

        assertEquals(3L, ingredientCommand.getId());
        assertEquals(1L, ingredientCommand.getRecipeId());

        verify(recipeRepository,times(1)).findById(1L);
        verify(converter,times(1)).convert(any());
    }
}