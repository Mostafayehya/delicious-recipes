package com.mostafayehya.deliciousrecipes.services;

import com.mostafayehya.deliciousrecipes.comands.IngredientCommand;
import com.mostafayehya.deliciousrecipes.converters.IngredientCommandToIngredient;
import com.mostafayehya.deliciousrecipes.converters.IngredientToIngredientCommand;
import com.mostafayehya.deliciousrecipes.domain.Ingredient;
import com.mostafayehya.deliciousrecipes.domain.Recipe;
import com.mostafayehya.deliciousrecipes.repositories.RecipeRepository;
import com.mostafayehya.deliciousrecipes.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand,
                                 IngredientCommandToIngredient ingredientCommandToIngredient, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientID(Long recipeId, Long ingredientId) {

        Optional<Recipe> optionalRecipe =
                recipeRepository.findById(recipeId);

        // todo: handle this case using Exceptions
        if (!optionalRecipe.isPresent())
            log.error("Recipe is not found !!");

        Recipe recipe = optionalRecipe.get();
        Optional<IngredientCommand> optionalIngredientCommand = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredientToIngredientCommand::convert).findFirst();

        if (!optionalIngredientCommand.isPresent()) {
            // todo implement error handling
            log.error("Couldn't find ingredient specified with id :" + ingredientId);
        }

        return optionalIngredientCommand.get();
    }


    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if (!recipeOptional.isPresent()) {
            log.debug("Recipe not found for id: " + command.getRecipeId());
            return new IngredientCommand();  // why we return a new IngredientCommand?
        } else {

            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> optionalIngredient = recipe.getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if (optionalIngredient.isPresent()) { // ingredient already found, we need to update it
                Ingredient foundIngredient = optionalIngredient.get();
                foundIngredient.setDescription(command.getDescription());
                foundIngredient.setAmount(command.getAmount());
                foundIngredient.setUom(unitOfMeasureRepository
                        .findById(command.getUnitOfMeasure().getId())
                        .orElseThrow(() -> new RuntimeException("UOM not found!!"))); // todo fix this
            } else {    // New ingredient, we need to save a new

                recipe.addIngredient(ingredientCommandToIngredient.convert(command));

            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            return savedRecipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst()
                    .map(ingredientToIngredientCommand::convert)
                    .get();

        }
    }
}
