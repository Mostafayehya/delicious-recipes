package com.mostafayehya.deliciousrecipes.controllers;

import com.mostafayehya.deliciousrecipes.domain.Category;
import com.mostafayehya.deliciousrecipes.domain.UnitOfMeasure;
import com.mostafayehya.deliciousrecipes.repositories.CategoriesRepository;
import com.mostafayehya.deliciousrecipes.repositories.UnitOfMeasureRepository;
import com.mostafayehya.deliciousrecipes.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
public class IndexController {

    private RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndex(Model model) {
        log.debug("index is requested !!");
        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }
}
