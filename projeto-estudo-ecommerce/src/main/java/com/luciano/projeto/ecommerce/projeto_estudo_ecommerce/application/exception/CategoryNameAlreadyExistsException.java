package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.exception;

public class CategoryNameAlreadyExistsException extends RuntimeException {

    public CategoryNameAlreadyExistsException() {
        super("Este nome de categoria já existe");
    }

}
