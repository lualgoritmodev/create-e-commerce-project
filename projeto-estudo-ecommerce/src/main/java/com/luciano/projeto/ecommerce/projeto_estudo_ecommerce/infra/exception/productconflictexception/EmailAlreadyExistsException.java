package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.exception.productconflictexception;

public class EmailAlreadyExistsException extends ConflictException {
    EmailAlreadyExistsException(String message) {
        super(message);
    }
}
