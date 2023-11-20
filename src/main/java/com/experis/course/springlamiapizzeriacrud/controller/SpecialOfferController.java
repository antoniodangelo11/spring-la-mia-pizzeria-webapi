package com.experis.course.springlamiapizzeriacrud.controller;

import com.experis.course.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import com.experis.course.springlamiapizzeriacrud.exceptions.SpecialOfferNotFoundException;
import com.experis.course.springlamiapizzeriacrud.model.Pizza;
import com.experis.course.springlamiapizzeriacrud.model.SpecialOffer;
import com.experis.course.springlamiapizzeriacrud.repository.PizzaRepository;
import com.experis.course.springlamiapizzeriacrud.repository.SpecialOfferRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Optional;

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

        specialOffer.setStartDate(LocalDate.now());
        specialOffer.setEndDate(LocalDate.now().plusMonths(5));
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

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<SpecialOffer> result = specialOfferRepository.findById(id);

        if (result.isPresent()) {
            model.addAttribute("specialOffer", result.get());
            return "offers/form";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "special offer with id " + id + " not found");
        }
    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer id,
                         @Valid @ModelAttribute("specialOffer") SpecialOffer formSpecialOffer,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "offers/form";
        } else {
            SpecialOffer specialOfferToEdit = specialOfferRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            specialOfferToEdit.setTitle(formSpecialOffer.getTitle());
            specialOfferToEdit.setStartDate(formSpecialOffer.getStartDate());
            specialOfferToEdit.setEndDate(formSpecialOffer.getEndDate());


            SpecialOffer savedSpecialOffer = specialOfferRepository.save(formSpecialOffer);
            return "redirect:/pizzas/show/" + savedSpecialOffer.getId();
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        SpecialOffer specialOfferToDelete = specialOfferRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        specialOfferRepository.deleteById(id);
        redirectAttributes.addFlashAttribute(
                "message",
                "Discount "
                        + specialOfferToDelete.getTitle()
                        + " of "
                        + specialOfferToDelete.getPizza().getName()
                        + " deleted!"
        );
        return "redirect:/pizzas/show/"  + specialOfferToDelete.getPizza().getId();
    }
}
