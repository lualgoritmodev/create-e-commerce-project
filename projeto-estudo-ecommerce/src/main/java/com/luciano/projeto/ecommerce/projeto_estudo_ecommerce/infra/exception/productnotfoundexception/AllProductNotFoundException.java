package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.exception.productnotfoundexception;

public class AllProductNotFoundException extends ResourceNotFoundException {
    public AllProductNotFoundException() {
        super("All Products Not Found");
    }
}
