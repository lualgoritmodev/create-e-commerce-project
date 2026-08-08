package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception;

public class InvalidNegativeQuantityException extends RuntimeException {

    public InvalidNegativeQuantityException() {
        super("O estoque não pode ser negativo.");
    };
}
