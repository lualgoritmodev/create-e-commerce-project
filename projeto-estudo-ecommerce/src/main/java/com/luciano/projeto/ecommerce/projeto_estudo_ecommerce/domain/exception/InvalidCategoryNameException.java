package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception;

public class InvalidCategoryNameException extends RuntimeException {
    public InvalidCategoryNameException() {
        super("Está categoria não existe");
    }
}
