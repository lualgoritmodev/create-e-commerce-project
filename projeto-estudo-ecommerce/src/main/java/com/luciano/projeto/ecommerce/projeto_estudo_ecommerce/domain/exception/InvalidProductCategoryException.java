package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception;

import java.util.UUID;

public class InvalidProductCategoryException extends RuntimeException {
    public InvalidProductCategoryException() {
        super("A categoria do produto é obrigatória.");
    }
}
