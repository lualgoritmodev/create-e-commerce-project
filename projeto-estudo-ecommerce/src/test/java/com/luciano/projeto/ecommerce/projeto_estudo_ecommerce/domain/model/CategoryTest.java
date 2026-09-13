package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception.InvalidCategoryIdException;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject.CategoryName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


public class CategoryTest {

    @Test
    void shouldCreateCategoryWithValidData() {

        CategoryName categoryName = new CategoryName("Gamer");
        Category category = Category.create(categoryName);

        assertEquals(categoryName.value(), category.getName().value());
        assertNotNull(category.getId());
        assertTrue(category.isEnabled());

    }

    @Test
    void shouldEnableCategory() {
        CategoryName categoryName = new CategoryName("Gamer");
        Category category = Category.create(categoryName);

        category.disable();
        category.enable();

        assertTrue(category.isEnabled());
    }

    @Test
    void shouldDisableCategory() {

        CategoryName categoryName = new CategoryName("Gamer");
        Category category = Category.create(categoryName);

        category.disable();

        assertEquals(false, category.isEnabled());

    }

    @Test
    void shouldRenameCategory() {
        CategoryName categoryName = new CategoryName("Gamer");
        Category category = Category.create(categoryName);

        category.rename(new CategoryName("Jogos"));

        assertEquals("Jogos", category.getName().value());

    }

    @Test
    void shouldRehydrateCategory() {
        UUID id = UUID.randomUUID();

        Category categoryVerify = Category.rehydrate(id,
                new CategoryName("Gamer"), false
        );

        assertEquals(id, categoryVerify.getId());
        assertEquals("Gamer", categoryVerify.getName().value());
        assertFalse(categoryVerify.isEnabled());

    }

    @Test
    void shouldThrowExceptionWhenRehydrateIdIsNull() {
        UUID id = null;

        assertThrows(
                  InvalidCategoryIdException.class,
                () ->  Category.rehydrate(id, new CategoryName("Gamer"), true)
        );

    }

}
