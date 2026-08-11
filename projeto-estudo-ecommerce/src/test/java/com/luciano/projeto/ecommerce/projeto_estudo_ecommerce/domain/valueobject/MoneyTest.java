package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception.InvalidMoneyException;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception.InvalidMoneyScaleException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    @Test
    void shouldAddTwoMoneyValues() {
        Money first = new Money(new BigDecimal("10.00"));
        Money second = new Money(new BigDecimal("5.00"));

        Money result = first.add(second);

        assertEquals(new BigDecimal("15.00"), result.value());
    }

    @Test
    void shouldSubtractTwoMoneyValues() {
        Money first = new Money(new BigDecimal("10.00"));
        Money second = new Money(new BigDecimal("5.00"));

        Money result = first.subtract(second);

        assertEquals(new BigDecimal("5.00"), result.value());
    }

    @Test
    void shouldMultiplyValueWithQuantity() {
        Money first = new Money(new BigDecimal("10.00"));
        int quantity =  3;

        Money result = first.multiply(quantity);

        assertEquals(new BigDecimal("30.00"), result.value());
    }

    @Test
    void shouldCreateMoneyWhenValueIsValid() {

        Money money = new Money(new BigDecimal("10.00"));

        assertEquals(new BigDecimal("10.00"), money.value());

    }

    @Test
    void shouldAcceptValueWithExtraTrailingZeros() {

        Money money = new Money(new BigDecimal("10.000"));

        assertEquals(new BigDecimal("10.00"), money.value());
    }

    @Test
    void shouldNotChangeOriginalMoneyWhenAdding() {
        Money first = new Money(new BigDecimal("10.00"));
        Money second = new Money(new BigDecimal("5.00"));

        Money result = first.add(second);

        assertNotSame(first, result);
        assertNotSame(second, result);
        assertEquals(new BigDecimal("10.00"), first.value());
        assertEquals(new BigDecimal("5.00"), second.value());
        assertEquals(new BigDecimal("15.00"), result.value());
    }

    @Test
    void shouldNormalizeMoneyScale() {

        Money money = new Money(new BigDecimal("10.0"));
        assertEquals(new BigDecimal("10.00"), money.value());
    }

    @Test
    void shouldBeEqualWhenValuesAreEquivalentAfterNormalization() {

        Money first = new Money(new BigDecimal("10.0"));
        Money second = new Money(new BigDecimal("10.00"));

        assertEquals(first, second);
    }

    @Test
    void shouldHaveSameHashCodeWhenValuesAreEquivalentAfterNormalization() {
        Money first = new Money(new BigDecimal("10.00"));
        Money second = new Money(new BigDecimal("10.0"));

        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void shouldReturnTrueWhenValueIsPositive() {
        Money money = new Money(new BigDecimal("1.0"));

        boolean result = money.isPositive();

        assertTrue(result);

    }

    @Test
    void shouldReturnTrueWhenValueIsNegative() {
        Money money = new Money(new BigDecimal("-1.0"));

        boolean result = money.isNegative();

        assertTrue(result);

    }

    @Test
    void shouldReturnTrueWhenValueIsZero() {
        Money money = new Money(new BigDecimal("0"));

        boolean result = money.isZero();

        assertTrue(result);
    }
    @Test
    void shouldThrowExceptionWhenValueIsNull() {
        InvalidMoneyException exception = assertThrows(
                InvalidMoneyException.class,
                () -> new Money(null)
        );

        assertEquals("O valor monetário é obrigatório.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenValueRequiresRounding() {
        InvalidMoneyScaleException exception = assertThrows(
                InvalidMoneyScaleException.class,
                () -> new Money(new BigDecimal("10.999"))
        );

        assertEquals("O valor monetário não pode exigir arredondamento para duas casas decimais.", exception.getMessage());
    }

}
