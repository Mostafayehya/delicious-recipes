package com.mostafayehya.deliciousrecipes.services;

import com.mostafayehya.deliciousrecipes.comands.IngredientCommand;
import com.mostafayehya.deliciousrecipes.comands.RecipeCommand;
import com.mostafayehya.deliciousrecipes.converters.IngredientToIngredientCommand;
import com.mostafayehya.deliciousrecipes.domain.Recipe;
import com.mostafayehya.deliciousrecipes.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand converter;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand converter) {
        this.recipeRepository = recipeRepository;
        this.converter = converter;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientID(Long recipeId, Long ingredientId) {

        Optional<Recipe> optionalRecipe =
                recipeRepository.findById(recipeId);

        // todo: handle this case using Exceptions
        if (!optionalRecipe.isPresent())
            log.error("Recipe is not found !!");

        Optional<IngredientCommand> optionalIngredientCommand = optionalRecipe.get().getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(converter::convert).findFirst();

        if (!optionalIngredientCommand.isPresent())
            log.error("Couldn't find ingredient specified with id :" + ingredientId);

        return optionalIngredientCommand.get();
    }
}
