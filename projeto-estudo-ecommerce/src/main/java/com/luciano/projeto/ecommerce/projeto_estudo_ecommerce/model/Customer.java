package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.model.utilenum.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "tb_customer")
public class Customer {
    @Id
    private UUID id;
    @NotBlank
    private String name;
    @CPF
    @NotBlank
    private String cpf;
    @Email
    @NotBlank
    private String email;

    private LocalDate birthDate;
    private Status status = Status.INACTIVE;

    private List<Order> orders = new ArrayList<>();

    private List<Address> addresses = new ArrayList<>();

    public Customer() {}
    public Customer(UUID id, String name, String cpf,
                    String email, LocalDate birthDate,
                    Status status, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.birthDate = birthDate;
        this.status = status;
        this.orders = orders;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> order) {
        this.orders = order;
    }

    public List<Address> getAddresses() {
        return addresses;
    }
    public void addOrder(Order order) {
        orders.add(order);
        order.setCustomer(this);
    }

    public void addAddress(Address address) {
        addresses.add(address);
        address.setCustomer(this);
    }

}
