package com.experis.course.springlamiapizzeriacrud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pizzas")
public class Pizza {

    // ATTRIBUTI

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name must not be blank")
    @Size(max = 255, message = "Length must be less than 255")
    private String name;

    @NotBlank(message = "Description must not be blank")
    private String description;

    @NotBlank(message = "Photo must not be blank")
    @Size(max = 255, message = "Length must be less than 255")
    private String photoUrl;

    @NotNull(message = "Price must not be null")
    @Min(value = 1, message = "Price must be greater than 0")
    @Max(value = 255, message = "Length must be less than 255")
    private float price;

    @OneToMany(mappedBy = "pizza")
    @JsonIgnore
    private List<SpecialOffer> specialOffers = new  ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Ingredient> ingredients;

    // GETTER E SETTER


    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<SpecialOffer> getSpecialOffers() {
        return specialOffers;
    }

    public void setSpecialOffers(List<SpecialOffer> specialOffers) {
        this.specialOffers = specialOffers;
    }
}
