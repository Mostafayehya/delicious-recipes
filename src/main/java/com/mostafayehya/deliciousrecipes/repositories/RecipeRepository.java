package com.mostafayehya.deliciousrecipes.repositories;

import com.mostafayehya.deliciousrecipes.domain.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
