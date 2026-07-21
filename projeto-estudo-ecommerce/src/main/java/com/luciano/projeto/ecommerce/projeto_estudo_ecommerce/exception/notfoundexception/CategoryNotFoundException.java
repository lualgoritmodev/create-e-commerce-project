package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.exception.notfoundexception;

import java.util.UUID;

public class CategoryNotFoundException extends ResourceNotFoundException {
    public CategoryNotFoundException(UUID categoryId) {
        super("Category not found: " + categoryId);
    }
}
