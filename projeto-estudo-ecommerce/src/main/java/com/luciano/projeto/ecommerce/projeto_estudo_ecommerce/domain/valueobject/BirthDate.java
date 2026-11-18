package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception.InvalidBirthDateException;

import java.time.LocalDate;
import java.time.Period;

public record BirthDate(LocalDate value) {

    public BirthDate {

        if(value == null) {
            throw new InvalidBirthDateException();
        }

    }

    public Period ageOn(LocalDate referenceDate) {

        if(referenceDate == null || value.isAfter(referenceDate)) {
            throw new InvalidBirthDateException();
        }

        return Period.between(value, referenceDate);
    }

}
