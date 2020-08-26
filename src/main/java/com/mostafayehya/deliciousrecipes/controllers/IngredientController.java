package com.mostafayehya.deliciousrecipes.controllers;

import com.mostafayehya.deliciousrecipes.comands.IngredientCommand;
import com.mostafayehya.deliciousrecipes.comands.RecipeCommand;
import com.mostafayehya.deliciousrecipes.services.IngredientService;
import com.mostafayehya.deliciousrecipes.services.RecipeService;
import com.mostafayehya.deliciousrecipes.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private UnitOfMeasureService unitOfMeasureService;


    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
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
        model.addAttribute("ingredient",ingredientService.findByRecipeIdAndIngredientID(recipeId,id));
        return "recipe/ingredient/show";

    }
    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients/{id}/update")
    public String updateRecipeIngredient(@PathVariable Long recipeId,
                                       @PathVariable Long id,
                                       Model model) throws Exception{

        model.addAttribute("ingredient",ingredientService.findByRecipeIdAndIngredientID(recipeId,id));
        model.addAttribute("uomList",unitOfMeasureService.listAllUoms());

        return "recipe/ingredient/ingredientform";

    }


    @PostMapping("recipe/{ingredientId}/ingredients")
        public String saveOrUpdate(@ModelAttribute IngredientCommand command){

        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);
        log.debug("saved receipe id:" + savedCommand.getRecipeId());
        log.debug("saved ingredient id:" + savedCommand.getId());

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredients/" + savedCommand.getId() + "/show";

    }
}
