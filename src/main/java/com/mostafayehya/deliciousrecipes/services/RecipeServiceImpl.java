package com.mostafayehya.deliciousrecipes.services;

import com.mostafayehya.deliciousrecipes.domain.Recipe;
import com.mostafayehya.deliciousrecipes.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
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

        Optional<Recipe> optionalRecipe =recipeRepository.findById(id);

        //todo redirect user to the error page instead
        if (!optionalRecipe.isPresent())
            throw new RuntimeException("Recipe not found");


        return optionalRecipe.get();
    }
}
