package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.exception.productnotfoundexception;

import java.util.UUID;

public class CategoryNotFoundException extends ResourceNotFoundException {
    public CategoryNotFoundException(UUID categoryId) {
        super("Category not found: " + categoryId);
    }
}
