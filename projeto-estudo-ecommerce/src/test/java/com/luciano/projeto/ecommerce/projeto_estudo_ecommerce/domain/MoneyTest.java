package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception.InvalidMoneyException;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception.InvalidMoneyScaleException;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyTest {

    @Test
    void shouldReturnTrueWhenValueIsPositive() {
        Money money = new Money(new BigDecimal("1.0"));

        boolean result = money.isPositive();

        assertTrue(result);

    }

    @Test
    void shouldReturntrueWhenValueIsNegative() {
        Money money = new Money(new BigDecimal("-1.0"));

        boolean result = money.isNegative();

        assertTrue(result);

    }

    @Test
    void shouldReturntrueWhenValueIsZero() {
        Money money = new Money(new BigDecimal("0"));

        boolean result = money.isZero();

        assertTrue(result);
    }
    @Test
    void shouldReturnExceptionWhenValueIsNull() {
        InvalidMoneyException exception = assertThrows(
                InvalidMoneyException.class,
                () -> new Money(null)
        );

        assertEquals("O valor monetário é obrigatório.", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionWhenScaleIsMoreTwo() {
        InvalidMoneyScaleException exception = assertThrows(
                InvalidMoneyScaleException.class,
                () -> new Money(new BigDecimal("10.999"))
        );

        assertEquals("O valor monetário deve possuir no máximo duas casas decimais.", exception.getMessage());
    }

}
