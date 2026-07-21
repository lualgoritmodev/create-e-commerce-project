package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.exception.notfoundexception;

public class AllProductNotFoundException extends ResourceNotFoundException {
    public AllProductNotFoundException() {
        super("All Products Not Found");
    }
}
