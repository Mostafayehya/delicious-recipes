package com.mostafayehya.deliciousrecipes.services;

import com.mostafayehya.deliciousrecipes.comands.IngredientCommand;
import com.mostafayehya.deliciousrecipes.comands.RecipeCommand;
import com.mostafayehya.deliciousrecipes.domain.Recipe;

import java.util.Set;

public interface RecipeService {

     Set<Recipe> getRecipes();
     Recipe findById(Long id);

     RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

     RecipeCommand findRecipeCommandById(Long id);

     void deleteById(Long id);

}
