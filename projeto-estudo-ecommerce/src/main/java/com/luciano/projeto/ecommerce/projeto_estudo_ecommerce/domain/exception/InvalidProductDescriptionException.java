package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception;

public class InvalidProductDescriptionException extends RuntimeException{

    public InvalidProductDescriptionException() {
        super("A descrição do produto não pode estar vazia.");
    }
}
