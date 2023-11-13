package com.experis.course.springlamiapizzeriacrud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

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

    // GETTER E SETTER

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public float getPrice() {
        return price;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
