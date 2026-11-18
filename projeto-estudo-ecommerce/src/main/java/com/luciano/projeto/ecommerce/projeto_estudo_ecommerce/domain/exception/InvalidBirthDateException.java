package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception;

public class InvalidBirthDateException extends RuntimeException {

    public InvalidBirthDateException() {
        super("Birth date is invalid");
    }

}
