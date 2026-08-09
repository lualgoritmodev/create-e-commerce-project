package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class ProductNameTest {
    @Test
    void shouldCreateProductNameWhenValueIsValid() {
        ProductName productName = new ProductName("Notebook Gamer");

        assertEquals("Notebook Gamer", productName.value());

    }
    @Test
    void shouldNormalizeProductName() {
        ProductName productName = new ProductName(" Notebook    Gamer ");

        assertEquals("Notebook Gamer", productName.value());
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

        ProductName productName = new ProductName("TV1");
        assertEquals("TV1", productName.value());
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

    @Test
    void shouldCreateProductNameWhenValueHasMaximumLength() {

        ProductName productName = new ProductName("A".repeat(100));

        assertEquals(100, productName.value().length());
    }

    @Test
    void shouldThrowExceptionWhenValueIsAboveMaximumLength() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new ProductName("A".repeat(101)));

        assertEquals("O nome do produto deve ter entre 3 e 100 caracteres.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "###",
            "@@@",
            "!!!",
            "---"
    })
    void shouldThrowExceptionWhenValueContainsOnlySymbols(String value) {

        IllegalArgumentException exception = assertThrows(
                 IllegalArgumentException.class,
                () -> new ProductName(value)
        );

        assertEquals("O nome do produto deve conter ao menos uma letra ou número.", exception.getMessage());
    }

    @Test
    void shouldBeEqualWhenValuesAreEquivalentAfterNormalization() {

        ProductName first = new ProductName("Notebook Gamer");
        ProductName second = new ProductName(" Notebook   Gamer");

        assertEquals(first, second);
    }

    @Test
    void shouldHaveSameHashCodeWhenValuesAreEquivalentAfterNormalization() {

        ProductName first = new ProductName("Notebook Gamer");
        ProductName second = new ProductName(" Notebook   Gamer");

        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void shouldNotBeEqualWhenValuesAreDifferent() {
        ProductName first = new ProductName("Notebook Gamer");
        ProductName second = new ProductName(" MOUSE ");

        assertNotEquals(first, second);
    }

}
