package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception.InvalidCategoryIdException;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception.InvalidCategoryNameException;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject.CategoryName;

import java.util.UUID;

public class Category {

    private UUID id;
    private CategoryName name;
    private boolean enabled;

    private Category() {}

    public UUID getId() {
        return id;
    }

    public CategoryName getName() {
        return name;
    }

    public boolean isEnabled() { return enabled; }

    public void enable() { this.enabled = true; }

    public void disable() { this.enabled = false; }

    public void rename(CategoryName name) {
        if(name == null) {
            throw new InvalidCategoryNameException();
        }

        this.name = name;
    }

    public static Category create(CategoryName name) {
        Category category = new Category();
        category.id = UUID.randomUUID();
        category.rename(name);
        category.enabled = true;

        return category;
    }

    public static Category rehydrate(UUID id, CategoryName name, boolean enabled) {
        validateIdForRehydration(id);

        Category category = new Category();
        category.id = id;
        category.rename(name);
        category.enabled = enabled;

        return category;
    }

    private static void validateIdForRehydration(UUID id) {
        if(id == null) {
            throw new InvalidCategoryIdException();
        }
    }

}
