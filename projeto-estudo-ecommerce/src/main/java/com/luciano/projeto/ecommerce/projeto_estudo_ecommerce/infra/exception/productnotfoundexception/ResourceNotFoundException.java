package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.exception.productnotfoundexception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

}
