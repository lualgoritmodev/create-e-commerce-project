package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.exception.conflictexception;

public class EmailAlreadyExistsException extends ConflictException {
    EmailAlreadyExistsException(String message) {
        super(message);
    }
}
