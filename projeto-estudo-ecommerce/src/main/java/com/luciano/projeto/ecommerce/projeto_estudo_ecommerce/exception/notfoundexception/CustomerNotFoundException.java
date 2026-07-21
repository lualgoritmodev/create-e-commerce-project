package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.exception.notfoundexception;

import java.util.UUID;

public class CustomerNotFoundException extends ResourceNotFoundException {
    public CustomerNotFoundException(UUID customerId) {
        super("Customer Not Found " + customerId);
    }

}
