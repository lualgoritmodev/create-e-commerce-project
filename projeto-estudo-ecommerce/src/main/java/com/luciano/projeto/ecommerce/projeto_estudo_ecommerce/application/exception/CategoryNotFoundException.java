package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.exception;

import java.util.UUID;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(UUID categoryId) {
        super("Category not found: " + categoryId);
    }

}
