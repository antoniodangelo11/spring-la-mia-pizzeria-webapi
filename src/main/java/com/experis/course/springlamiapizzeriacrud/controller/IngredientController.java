package com.experis.course.springlamiapizzeriacrud.controller;

import com.experis.course.springlamiapizzeriacrud.model.Ingredient;
import com.experis.course.springlamiapizzeriacrud.model.Pizza;
import com.experis.course.springlamiapizzeriacrud.model.SpecialOffer;
import com.experis.course.springlamiapizzeriacrud.repository.IngredientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping
    public String index(Model model){
        List<Ingredient> ingredientList;

        ingredientList = ingredientRepository.findByOrderByName();
        model.addAttribute("ingredientList", ingredientList);
        model.addAttribute("ingredientObj", new Ingredient());

        return "ingredients/list";
    }

    @PostMapping
    public String doSave(@Valid @ModelAttribute("ingredientObj") Ingredient formIngredient,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ingredients/form";
        }
        Ingredient savedIngredient = ingredientRepository.save(formIngredient);
        return "redirect:/ingredients";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {

        Ingredient ingredientToDelete = ingredientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        ingredientRepository.deleteById(id);
        redirectAttributes.addFlashAttribute(

                "Ingredient "
                        + ingredientToDelete.getName()
                        + " deleted!");

        return "redirect:/ingredients";
    }
}
