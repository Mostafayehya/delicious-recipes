package com.mostafayehya.deliciousrecipes.repositories;

import com.mostafayehya.deliciousrecipes.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,Long> {

    Optional<UnitOfMeasure> findByUom(String uom);

}
