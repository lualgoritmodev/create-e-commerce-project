package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.adapter.in.dto.response;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.application.port.dto.CategoryResult;
import java.util.UUID;

public record CategoryResponse(
        UUID id,
        String name,
        boolean isEnabled
) {}
