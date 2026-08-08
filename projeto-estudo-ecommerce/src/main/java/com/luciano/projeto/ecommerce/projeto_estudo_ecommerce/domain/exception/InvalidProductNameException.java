package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception;

public class InvalidProductNameException extends RuntimeException {
    public InvalidProductNameException() {
        super("O nome do produto não pode estar vazio.");
    }
}
