package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception;

public class InvalidMoneyScaleException extends RuntimeException {

    public InvalidMoneyScaleException() {
        super("O valor monetário não pode exigir arredondamento para duas casas decimais.");
    }
}
