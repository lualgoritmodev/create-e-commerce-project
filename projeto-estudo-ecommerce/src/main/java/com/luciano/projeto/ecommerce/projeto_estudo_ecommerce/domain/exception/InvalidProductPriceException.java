package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception;

public class InvalidProductPriceException extends RuntimeException {

    public InvalidProductPriceException() {
        super("O preço do produto deve ser maior que zero.");
    }
}
