package com.mostafayehya.deliciousrecipes.controllers;

import com.mostafayehya.deliciousrecipes.comands.RecipeCommand;
import com.mostafayehya.deliciousrecipes.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IngredientController {

    private final RecipeService recipeService;


    public IngredientController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String getIngredients(@PathVariable Long recipeId, Model model) throws Exception {

        RecipeCommand recipeCommand = recipeService.findRecipeCommandById(recipeId);

        model.addAttribute("recipe",recipeCommand);
        return "recipe/ingredient/list";
    }
}
