package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.exception;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.exception.productnotfoundexception.ResourceNotFoundException;

import java.util.UUID;

public class CategoryNotFoundException extends ResourceNotFoundException {
    public CategoryNotFoundException(UUID categoryId) {
        super("Category not found: " + categoryId);
    }

}
