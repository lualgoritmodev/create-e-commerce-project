package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model.utilenum.CustomerStatus;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject.BirthDate;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject.Cpf;
import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject.CustomerName;

import java.util.UUID;

public class Customer {

    private UUID id;
    private CustomerName name;
    private Cpf cpf;
    private String email;
    private BirthDate birthDate;
    private CustomerStatus status;

    private Customer() {
    }

    public CustomerStatus getStatus() {
        return status;
    }

}
