package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception;

public class InvalidCategoryIdException extends RuntimeException {
    public InvalidCategoryIdException() {
        super("Category id must not be null");
    }
}
