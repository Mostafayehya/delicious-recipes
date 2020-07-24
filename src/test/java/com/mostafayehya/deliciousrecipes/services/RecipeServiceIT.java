package com.mostafayehya.deliciousrecipes.services;

import com.mostafayehya.deliciousrecipes.comands.RecipeCommand;
import com.mostafayehya.deliciousrecipes.converters.RecipeCommandToRecipe;
import com.mostafayehya.deliciousrecipes.converters.RecipeToRecipeCommand;
import com.mostafayehya.deliciousrecipes.domain.Recipe;
import com.mostafayehya.deliciousrecipes.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT {

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    String DESCRIPTION="description";

    @Before
    public void setUp() throws Exception {
    }


    @Transactional
    @Test
    public void saveRecipecommand() {

        // given
        Iterable<Recipe> recipeIterable = recipeRepository.findAll();
        Recipe recipe = recipeIterable.iterator().next();
        RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe);

        // when
        recipeCommand.setDescription(DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipecommand(recipeCommand);

        // then
        assertEquals(DESCRIPTION,savedRecipeCommand.getDescription());
        assertEquals(recipe.getId(),savedRecipeCommand.getId());
        assertEquals(recipe.getIngredients().size(),savedRecipeCommand.getIngredients().size());
    }
}