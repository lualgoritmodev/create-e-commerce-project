package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(@NotBlank String name) {}