package com.experis.course.springlamiapizzeriacrud.controller;

import com.experis.course.springlamiapizzeriacrud.model.Pizza;
import com.experis.course.springlamiapizzeriacrud.repository.IngredientRepository;
import com.experis.course.springlamiapizzeriacrud.repository.PizzaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/pizzas")
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping
    public String index(@RequestParam Optional<String> search, Model model) {
        List<Pizza> pizzaList;

        if (search.isPresent()) {
            pizzaList = pizzaRepository.findByNameContainingIgnoreCase(search.get());
        } else {
            pizzaList = pizzaRepository.findAll();
        }
        model.addAttribute("pizzaList", pizzaList);
        return "pizzas/list";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("pizza", result.get());
            return "pizzas/show";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "pizza with id " + id + " not found");
        }
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredientList", ingredientRepository.getAll());
        return "pizzas/form";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("pizza") Pizza formPizza,
                           BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "pizzas/form";
        }

        Pizza savedPizza = null;
        try {
            savedPizza = pizzaRepository.save(formPizza);
        } catch (RuntimeException e) {
            bindingResult.addError(new FieldError(
                    "The",
                    "name", formPizza.getName(),
                    false,
                    null,
                    null,
                    "Name must be unique"));
            model.addAttribute("ingredientList", ingredientRepository.getAll());
            return "pizzas/form";
        }
        return "redirect:/pizzas/show/" + savedPizza.getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("pizza", result.get());
            model.addAttribute("ingredientList", ingredientRepository.getAll());
            return "/pizzas/form";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found");
        }
    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("pizza") Pizza formPizza,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/pizzas/form";
        }

        Pizza pizzaToEdit = pizzaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        pizzaToEdit.setName(formPizza.getName());
        pizzaToEdit.setDescription(formPizza.getDescription());
        pizzaToEdit.setPhotoUrl(formPizza.getPhotoUrl());
        pizzaToEdit.setPrice(formPizza.getPrice());

        Pizza savedPizza = pizzaRepository.save(pizzaToEdit);
        model.addAttribute("ingredientList", ingredientRepository.getAll());
        return "redirect:/pizzas/show/" + savedPizza.getId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {

        Pizza pizzaToDelete = pizzaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        pizzaRepository.deleteById(id);
        redirectAttributes.addFlashAttribute(
                "message",
                "The "
                        + pizzaToDelete.getName()
                        + " deleted!");
        return "redirect:/pizzas";
    }
}
