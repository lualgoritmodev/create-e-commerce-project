package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject.ProductName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductNameTest {
    @Test
    void shouldCreateProductNameWhenValueIsValid() {
        ProductName expect = new ProductName("Notebook Gamer");

        assertEquals("Notebook Gamer", expect.value());
        assertNotEquals(" ", expect.value());

    }
    @Test
    void shouldNormalizeProductName() {
        ProductName expect = new ProductName(" Notebook    Gamer ");

        assertEquals("Notebook Gamer", expect.value());
    }
    @Test
    void shouldThrowExceptionWhenValueIsBlank() {
        IllegalArgumentException exception = assertThrows(
           IllegalArgumentException.class,
                () -> new ProductName("  ")
        );

        assertEquals("O nome do produto não pode estar vazio.", exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenValueIsNull() {

       IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new ProductName(null)
        );

       assertEquals("O nome do produto é obrigatório.", exception.getMessage());

    }
    @Test
    void shouldCreateProductNameWhenValueHasMinimumLength() {

        ProductName expect = new ProductName("TV1");
        assertEquals("TV1", expect.value());
    }
    @Test
    void shouldThrowExceptionWhenValueIsBelowMinimumLength() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new ProductName("TV")
        );

        assertEquals("O nome do produto deve ter entre 3 e 100 caracteres.",
                exception.getMessage());
    }

}
