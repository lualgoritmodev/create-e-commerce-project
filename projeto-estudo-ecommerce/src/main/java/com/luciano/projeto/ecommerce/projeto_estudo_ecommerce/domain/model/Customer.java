package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model.utilenum.CustomerStatus;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject.CustomerName;

import java.time.LocalDate;
import java.util.UUID;

public class Customer {

    private UUID id;
    private CustomerName name;
    private String cpf;
    private String email;
    private LocalDate birthDate;
    private CustomerStatus status;

    private Customer() {
    }

    public CustomerStatus getStatus() {
        return status;
    }

}
