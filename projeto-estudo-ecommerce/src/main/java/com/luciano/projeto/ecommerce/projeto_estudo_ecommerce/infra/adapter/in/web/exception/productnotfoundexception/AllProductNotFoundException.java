package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.web.exception.productnotfoundexception;

public class AllProductNotFoundException extends ResourceNotFoundException {
    public AllProductNotFoundException() {
        super("All Products Not Found");
    }
}
