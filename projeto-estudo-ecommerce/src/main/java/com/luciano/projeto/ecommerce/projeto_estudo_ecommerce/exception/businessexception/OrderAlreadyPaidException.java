package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.exception.businessexception;

public class OrderAlreadyPaidException extends BusinessException {
    OrderAlreadyPaidException(String message) {
        super(message);
    }
}
