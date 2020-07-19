package com.mostafayehya.deliciousrecipes.repositories;

import com.mostafayehya.deliciousrecipes.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoriesRepository extends CrudRepository<Category, Long> {
}
