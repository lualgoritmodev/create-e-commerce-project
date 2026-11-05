package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception.InvalidCategoryNameException;

import java.util.regex.Pattern;

public record CategoryName(String value) {

    private static final Pattern MULTIPLE_SPACES = Pattern.compile("\\s+");

    public CategoryName {value = validateAndNormalize(value); }

    private static String validateAndNormalize(String value) {
        validateRequired(value);

        String normalized = normalize(value);

        return normalized;
    }

    private static void validateRequired(String value) {
        if(value == null || value.isBlank()) {
            throw new InvalidCategoryNameException();
        }

    }

    private static String normalize(String value) {
        String strippedValue  = value.strip();
        return MULTIPLE_SPACES.matcher(strippedValue).replaceAll(" ");

    }

}
