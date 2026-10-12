package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.web.exception.productbusinessexception;

public class OrderAlreadyPaidException extends BusinessException {
    OrderAlreadyPaidException(String message) {
        super(message);
    }
}
