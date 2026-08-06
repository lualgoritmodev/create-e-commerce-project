package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject.ProductName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProductNameTest {

    @Test
    void shouldCreateProductNameWhenValueIsValid() {
        ProductName expect = new ProductName("Notebook Gamer");
        assertEquals("Notebook Gamer", expect.value());
        assertNotEquals(" ", expect.value());
    }

}
