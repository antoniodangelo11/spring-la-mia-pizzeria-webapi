package com.experis.course.springlamiapizzeriacrud.controller;

import com.experis.course.springlamiapizzeriacrud.exceptions.SpecialOfferNotFoundException;
import com.experis.course.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import com.experis.course.springlamiapizzeriacrud.model.SpecialOffer;
import com.experis.course.springlamiapizzeriacrud.service.SpecialOfferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/offers")
public class SpecialOfferController {

    @Autowired
    SpecialOfferService specialOfferService;

    @GetMapping("/create")
    public String create(@RequestParam Integer pizzaId, Model model) {
        try {
            model.addAttribute("special offer", specialOfferService.createSpecialOffer(pizzaId));
            return "offers/form";
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("special offer") SpecialOffer formSpecialOffer,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "offers/form";
        }
        SpecialOffer saveSpecialOffer = specialOfferService.saveSpecialOffer(formSpecialOffer);
        return "redirect:/pizzas/show/" + formSpecialOffer.getPizza().getId();
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        try {
            SpecialOffer specialOffer = specialOfferService.getSpecialOffer(id);
            model.addAttribute("special offer", specialOffer);
            return "offers/form";
        } catch (SpecialOfferNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer id,
                         @Valid @ModelAttribute("special offer") SpecialOffer formSpecialOffer,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "offers/form";
        }
        SpecialOffer saveSpecialOffer = specialOfferService.saveSpecialOffer(formSpecialOffer);
        return "redirect:/pizzas/show/" + formSpecialOffer.getPizza().getId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            SpecialOffer specialOfferToDelete = specialOfferService.getSpecialOffer(id);
            specialOfferService.deleteSpecialOffer(specialOfferToDelete);
            redirectAttributes.addFlashAttribute(
                    "message",
                    " Special Offer deleted!");
            return "redirect:/pizzas/show/" + specialOfferToDelete.getPizza().getId();
        } catch (SpecialOfferNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
