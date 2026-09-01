package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception.InvalidCategoryNameException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryNameTest {

    @Test
    void shouldCreateCategoryNameWithValidName() {

        CategoryName categoryName= new CategoryName("Gamer");

        assertEquals("Gamer", categoryName.value());

    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        InvalidCategoryNameException exception = assertThrows(
                InvalidCategoryNameException.class,
                () -> new CategoryName(null)
        );

        assertEquals("Está categoria não existe", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsBlank() {
        InvalidCategoryNameException exception = assertThrows(
                 InvalidCategoryNameException.class,
                () -> new CategoryName(" ")
        );

        assertEquals("Está categoria não existe", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsNotNormalize() {
        assertThrows(
                InvalidCategoryNameException.class,
                () -> new CategoryName("Gamer 123")
        );
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {
        assertThrows(
                InvalidCategoryNameException.class,
                () -> new CategoryName("")
        );
    }

    @Test
    void shouldNormalizeCategoryName() {
        CategoryName categoryName = new CategoryName("  Eletrônicos  e Acessorios      ");
        assertEquals("Eletrônicos e Acessorios", categoryName.value());
    }

    @Test
    void shouldBeEqualWhenValuesAreEquivalentAfterNormalization() {

        CategoryName first = new CategoryName(" Gamer     ");
        CategoryName second = new CategoryName("Gamer");

        assertEquals(first, second);
    }

    @Test
    void shouldHaveSameHashCodeWhenValuesAreEquivalentAfterNormalization() {
        CategoryName first = new CategoryName(" Gamer     ");
        CategoryName second = new CategoryName("Gamer");

        assertEquals(first.hashCode(), second.hashCode());

    }

}
