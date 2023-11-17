package com.experis.course.springlamiapizzeriacrud.repository;

import java.util.List;

import com.experis.course.springlamiapizzeriacrud.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    List<Ingredient>  findByOrderByName();

    public default List<Ingredient> getAll() {
        return findByOrderByName();
    }

    boolean existsByName(String name);

}
