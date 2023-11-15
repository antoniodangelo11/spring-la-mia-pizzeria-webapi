package com.experis.course.springlamiapizzeriacrud.controller;

import com.experis.course.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import com.experis.course.springlamiapizzeriacrud.model.Pizza;
import com.experis.course.springlamiapizzeriacrud.model.SpecialOffer;
import com.experis.course.springlamiapizzeriacrud.repository.PizzaRepository;
import com.experis.course.springlamiapizzeriacrud.repository.SpecialOfferRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/offers")
public class SpecialOfferController {

    @Autowired
    private SpecialOfferRepository specialOfferRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping("/create")
    public String create(@RequestParam Integer pizzaId, Model model) {
        Pizza pizza = pizzaRepository.findById(pizzaId)
                .orElseThrow(() -> new PizzaNotFoundException("Pizza not " + "found"));

        SpecialOffer specialOffer = new SpecialOffer();
        specialOffer.setPizza(pizza);

        model.addAttribute("specialOffer", specialOffer);
        return "offers/form";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("specialOffer") SpecialOffer formSpecialOffer,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "offers/form";
        }
        SpecialOffer savedSpecialOffer = specialOfferRepository.save(formSpecialOffer);
        return "redirect:/pizzas/show/" + formSpecialOffer.getPizza().getId();
    }

}
