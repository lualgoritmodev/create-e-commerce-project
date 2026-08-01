package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.dominio.valueobject;

import java.util.regex.Pattern;

public record ProductName(String value) {

    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 100;

    private static final Pattern MULTIPLE_SPACES =
            Pattern.compile("\\s+");

    private static final Pattern CONTAINS_LETTER_OR_NUMBER =
            Pattern.compile(".*[\\p{L}\\p{N}].*");

    public ProductName { value = validateAndNormalize(value); }

    private static String validateAndNormalize(String value) {

        validateRequired(value);

        String normalized = normalize(value);
        validateNotBlank(normalized);
        validateLength(normalized);
        validateContent(normalized);

        return normalized;
    }
    private static void validateRequired(String value) {
        if(value == null) {
            throw new IllegalArgumentException(
                    "O nome do produto é obrigatório."
            );
        }
    }

    private static void validateNotBlank(String value) {
            if (value.isBlank()) {
                throw new IllegalArgumentException(
                        "O nome do produto não pode estar vazio."
                );
            }
    }

    private static void validateLength(String value) {
        if (value.length() < MIN_LENGTH || value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(
                    "O nome do produto deve ter entre "
                            + MIN_LENGTH
                            + " e "
                            + MAX_LENGTH
                            + " caracteres."
            );
        }
    }

    private static void validateContent(String value) {
        if (!CONTAINS_LETTER_OR_NUMBER.matcher(value).matches()) {
            throw new IllegalArgumentException(
                    "O nome do produto deve conter ao menos uma letra ou número."
            );
        }
    }
    private static String normalize(String value) {
        String trimmedValue = value.trim();

        return MULTIPLE_SPACES
                .matcher(trimmedValue)
                .replaceAll(" ");
    }

}
