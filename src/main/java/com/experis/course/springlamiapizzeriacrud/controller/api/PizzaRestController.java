package com.experis.course.springlamiapizzeriacrud.controller.api;

import com.experis.course.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import com.experis.course.springlamiapizzeriacrud.model.Pizza;
import com.experis.course.springlamiapizzeriacrud.service.PizzaService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/pizzas")
@CrossOrigin
public class PizzaRestController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public List<Pizza> index(@RequestParam Optional<String> search) {
        return pizzaService.getPizzaList(search);
    }

    @GetMapping("/{id}")
    public Pizza details(@PathVariable Integer id) {
        try {
            return pizzaService.getPizzaById(id);
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public Pizza create(@Valid @RequestBody Pizza pizza) {
        try {
            return pizzaService.createPizza(pizza);
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Pizza update(@PathVariable Integer id, @Valid @RequestBody Pizza pizza) {
        pizza.setId(id);
        try {
            return pizzaService.editPizza(pizza);
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            Pizza pizzaToDelete = pizzaService.getPizzaById(id);
            pizzaService.deletePizza(id);
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/page")
    public Page<Pizza> pagedIndex(
            @RequestParam(name = "size", defaultValue = "2") Integer size,
            @RequestParam(name = "page", defaultValue = "0") Integer page) {

        return pizzaService.getPage(PageRequest.of(page, size));
    }

    @GetMapping("/page/v2")
    public Page<Pizza> pagedIndexV2(@PageableDefault(page = 0, size = 2) Pageable pageable) {
        return pizzaService.getPage(pageable);
    }
}
