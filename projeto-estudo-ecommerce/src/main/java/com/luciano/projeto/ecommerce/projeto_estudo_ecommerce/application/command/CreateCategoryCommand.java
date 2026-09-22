package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.command;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryCommand (@NotBlank String name) {
}
