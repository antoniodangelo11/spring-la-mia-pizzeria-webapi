package com.experis.course.springlamiapizzeriacrud.service;

import com.experis.course.springlamiapizzeriacrud.exceptions.IngredientNameUniqueException;
import com.experis.course.springlamiapizzeriacrud.model.Ingredient;
import com.experis.course.springlamiapizzeriacrud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> getAll() {
        return ingredientRepository.findByOrderByName();
    }

    public Ingredient getIngredientById(Integer id) throws IngredientNameUniqueException {
        Optional<Ingredient> result = ingredientRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new IngredientNameUniqueException("ingredient with id " + id + " not found");
        }
    }

    public void saveIngredient(Ingredient ingredient) throws IngredientNameUniqueException {
        if (ingredientRepository.existsByName(ingredient.getName())) {
            throw new IngredientNameUniqueException(ingredient.getName());
        }
        ingredient.setName(ingredient.getName().toLowerCase());
        ingredientRepository.save(ingredient);
    }

    public void deleteIngredient(Integer id) {
        ingredientRepository.deleteById(id);
    }
}