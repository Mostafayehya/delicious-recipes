package com.mostafayehya.deliciousrecipes.services;

import com.mostafayehya.deliciousrecipes.comands.RecipeCommand;
import com.mostafayehya.deliciousrecipes.converters.RecipeCommandToRecipe;
import com.mostafayehya.deliciousrecipes.converters.RecipeToRecipeCommand;
import com.mostafayehya.deliciousrecipes.domain.Recipe;
import com.mostafayehya.deliciousrecipes.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeToRecipeCommand recipeToRecipeCommand;
    private final RecipeCommandToRecipe recipeCommandToRecipe;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeToRecipeCommand recipeToRecipeCommand, RecipeCommandToRecipe recipeCommandToRecipe) {
        this.recipeRepository = recipeRepository;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("I'm inside Recipe Service");
        Set<Recipe> recipes = new HashSet<>();
        // Converting iterable to a HashSet
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    public Recipe findById(Long id) {

        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);

        //todo redirect user to the error page instead
        if (!optionalRecipe.isPresent())
            throw new RuntimeException("Recipe not found");


        return optionalRecipe.get();
    }

    @Transactional // I need to know why we used this
    @Override
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {

        Recipe recipe = recipeCommandToRecipe.convert(recipeCommand);
        Recipe returnedRecipe = recipeRepository.save(recipe);
        log.debug("Saved Recipe Id " + returnedRecipe.getId());
        return recipeToRecipeCommand.convert(returnedRecipe);
    }

    @Transactional
    @Override
    public RecipeCommand findRecipeCommandById(Long id) {
        return recipeToRecipeCommand.convert(findById(id));
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}
