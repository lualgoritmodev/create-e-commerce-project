package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.exception.productbusinessexception;

public class OrderAlreadyPaidException extends BusinessException {
    OrderAlreadyPaidException(String message) {
        super(message);
    }
}
