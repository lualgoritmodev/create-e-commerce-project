package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.exception.notfoundexception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

}
