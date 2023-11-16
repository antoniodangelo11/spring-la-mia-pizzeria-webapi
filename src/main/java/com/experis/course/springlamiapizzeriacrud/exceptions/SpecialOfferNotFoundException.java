package com.experis.course.springlamiapizzeriacrud.exceptions;

import com.experis.course.springlamiapizzeriacrud.model.SpecialOffer;

public class SpecialOfferNotFoundException extends RuntimeException {

    public SpecialOfferNotFoundException(String message) {
        super(message);
    }
}
