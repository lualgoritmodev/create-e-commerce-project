package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RenameCategoryRequest(@NotBlank String name) {
}
