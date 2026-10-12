package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.web.exception.productconflictexception;

public class EmailAlreadyExistsException extends ConflictException {
    EmailAlreadyExistsException(String message) {
        super(message);
    }
}
