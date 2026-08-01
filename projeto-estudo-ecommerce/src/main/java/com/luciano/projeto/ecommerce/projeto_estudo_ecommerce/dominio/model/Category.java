package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.dominio.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.util.UUID;
@Table("tb_category")
public class Category {
    @Id
    private UUID id;
    @NotBlank
    @Column("name")
    private String name;
    @NotNull
    @Column("active")
    private Boolean active = true;

    public Category() {}
    public Category(UUID id, String name,
                    Boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}
