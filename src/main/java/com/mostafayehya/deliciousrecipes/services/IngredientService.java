package com.mostafayehya.deliciousrecipes.services;

import com.mostafayehya.deliciousrecipes.comands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientID(Long recipeId,Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);
}
