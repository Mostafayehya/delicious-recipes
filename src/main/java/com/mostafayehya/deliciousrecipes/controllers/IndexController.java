package com.mostafayehya.deliciousrecipes.controllers;

import com.mostafayehya.deliciousrecipes.domain.Category;
import com.mostafayehya.deliciousrecipes.domain.UnitOfMeasure;
import com.mostafayehya.deliciousrecipes.repositories.CategoriesRepository;
import com.mostafayehya.deliciousrecipes.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoriesRepository categoriesRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoriesRepository categoriesRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoriesRepository = categoriesRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndex() {

        Optional<Category> category = categoriesRepository.findByDescription("American");
        System.out.println(category.get().getId());

        Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findByUom("Ounce");
        System.out.println(uom.get().getId());


        return "index";
    }
}
