package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception;

public class InvalidMoneyException extends RuntimeException {

    public InvalidMoneyException() {
        super("O valor monetário é obrigatório.");
    }
}
