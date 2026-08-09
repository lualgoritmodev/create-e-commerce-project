package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception;

public class InvalidMoneyScaleException extends RuntimeException {

    public InvalidMoneyScaleException() {
        super("O valor monetário deve possuir no máximo duas casas decimais.");
    }
}
