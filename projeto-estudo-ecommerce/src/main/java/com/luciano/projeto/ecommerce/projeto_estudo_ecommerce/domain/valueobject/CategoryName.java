package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception.InvalidCategoryNameException;

import java.util.regex.Pattern;

public record CategoryName(String value) {

    private static final Pattern MULTIPLE_SPACES = Pattern.compile("\\s+");
    private static final Pattern CONTAINS_NUMBER = Pattern.compile("\\p{N}");

    public CategoryName {value = validateAndNormalize(value); }

    private static String validateAndNormalize(String value) {
        validateRequired(value);

        String normalized = normalize(value);
        validateContent(normalized);

        return normalized;
    }

    private static void validateRequired(String value) {
        if(value == null || value.isBlank()) {
            throw new InvalidCategoryNameException();
        }

    }

    private static String normalize(String value) {
        String trimmedValue = value.trim();
        return MULTIPLE_SPACES.matcher(trimmedValue).replaceAll(" ");

    }

    private static void validateContent(String value) {
        if(CONTAINS_NUMBER.matcher(value).find()) {
            throw new InvalidCategoryNameException();
        }
    }

}
