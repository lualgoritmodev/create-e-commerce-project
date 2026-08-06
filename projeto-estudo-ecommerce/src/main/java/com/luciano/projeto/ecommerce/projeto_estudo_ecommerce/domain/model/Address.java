package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("tb_address")
public class Address {

    @Id
    private UUID id;

    @NotBlank
    @Column("street")
    private String street;

    @NotBlank
    @Column("number")
    private String number;
    @NotBlank
    @Column("complement")
    private String complement;

    @NotBlank
    @Column("neighborhood")
    private String neighborhood;

    @NotBlank
    @Column("city")
    private String city;

    @NotBlank
    @Column("state")
    private String state;

    @NotBlank
    @Column("zip_code")
    private String zipCode;

    @NotNull
    @Column("customer_id")
    private UUID customerId;

    public Address() {
    }
    public Address(UUID id, String street, String number,
                   String complement, String neighborhood,
                   String city, String state, String zipCode,
                   UUID customer) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.customerId = customer;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

}
