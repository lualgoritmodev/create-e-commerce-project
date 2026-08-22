package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception;

public class InvalidNameOfCustomer extends RuntimeException {
    public InvalidNameOfCustomer() {
        super("O nome do cliente é obrigatório");
    }
}
