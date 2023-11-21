package com.experis.course.springlamiapizzeriacrud.service;

import com.experis.course.springlamiapizzeriacrud.exceptions.SpecialOfferNotFoundException;
import com.experis.course.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import com.experis.course.springlamiapizzeriacrud.model.SpecialOffer;
import com.experis.course.springlamiapizzeriacrud.model.Pizza;
import com.experis.course.springlamiapizzeriacrud.repository.SpecialOfferRepository;
import com.experis.course.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SpecialOfferService {
    @Autowired
    PizzaRepository pizzaRepository;
    @Autowired
    SpecialOfferRepository specialOfferRepository;

    public SpecialOffer createSpecialOffer(Integer pizzaId) throws PizzaNotFoundException {
        Pizza pizza = pizzaRepository.findById(pizzaId).orElseThrow(()
                -> new PizzaNotFoundException("Pizza with id " + pizzaId + " not found"));
        SpecialOffer discount = new SpecialOffer();
        discount.setStartDate(LocalDate.now());
        discount.setEndDate(LocalDate.now().plusWeeks(1));
        discount.setPizza(pizza);
        return discount;
    }

    public SpecialOffer saveSpecialOffer(SpecialOffer specialOffer) {
        return specialOfferRepository.save(specialOffer);
    }

    public SpecialOffer getSpecialOffer(Integer id) throws SpecialOfferNotFoundException {
        return specialOfferRepository.findById(id).orElseThrow(()
                -> new SpecialOfferNotFoundException("Discount with id " + id + " not " +
                "found"));
    }

    public void deleteSpecialOffer(SpecialOffer specialOffer) {
        specialOfferRepository.delete(specialOffer);
    }
}
