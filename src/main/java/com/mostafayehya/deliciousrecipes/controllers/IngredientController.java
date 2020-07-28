package com.mostafayehya.deliciousrecipes.controllers;

import com.mostafayehya.deliciousrecipes.comands.RecipeCommand;
import com.mostafayehya.deliciousrecipes.services.IngredientService;
import com.mostafayehya.deliciousrecipes.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;


    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String getIngredients(@PathVariable Long recipeId, Model model) throws Exception {

        RecipeCommand recipeCommand = recipeService.findRecipeCommandById(recipeId);

        model.addAttribute("recipe",recipeCommand);
        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients/{id}/show")
    public String showRecipeIngredient(@PathVariable Long recipeId,
                                       @PathVariable Long id,
                                       Model model) throws Exception{
     log.debug("showIngredient is called ----------------------->");
        model.addAttribute("ingredient",ingredientService.findByRecipeIdAndIngredientID(recipeId,id));
        return "recipe/ingredient/show";

    }
}
