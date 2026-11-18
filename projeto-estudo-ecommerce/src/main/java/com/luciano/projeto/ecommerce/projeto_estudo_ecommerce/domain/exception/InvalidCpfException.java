package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception;

public class InvalidCpfException extends RuntimeException {
    public InvalidCpfException() {
        super("CPF is Invalid");
    }
}
