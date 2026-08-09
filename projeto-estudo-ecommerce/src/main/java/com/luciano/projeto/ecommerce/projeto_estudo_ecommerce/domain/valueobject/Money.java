package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception.InvalidMoneyException;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception.InvalidMoneyScaleException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Money(BigDecimal value) {

    private static final int SCALE = 2;

    public Money {
        validateRequiredMoney(value);
        value = normalizeScale(value);
    }

    public boolean isPositive() {
        return value.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isNegative() {
        return value.compareTo(BigDecimal.ZERO) < 0;
    }

    public boolean isZero() {
        return value.compareTo(BigDecimal.ZERO) == 0;
    }

    public Money add(Money money) {
        return new Money(this.value.add(money.value));
    }

    public Money subtract(Money money) {
        return new Money(this.value.subtract(money.value));
    }

    public Money multiply(int quantity) {
        return new Money(
                this.value.multiply(
                        BigDecimal.valueOf(quantity)
                )
        );
    }

    public static void validateRequiredMoney(BigDecimal value) {
        if(value == null) {
            throw new InvalidMoneyException();
        }
    }

    private static BigDecimal normalizeScale(BigDecimal value) {
        try {
            return value.setScale(
                    SCALE,
                    RoundingMode.UNNECESSARY
            );
        } catch (ArithmeticException exception) {
            throw new InvalidMoneyScaleException();
        }

    }

}
