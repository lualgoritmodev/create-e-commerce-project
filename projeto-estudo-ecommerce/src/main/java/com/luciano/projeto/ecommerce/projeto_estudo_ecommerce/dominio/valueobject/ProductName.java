package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.dominio.valueobject;

import java.util.regex.Pattern;

public final class ProductName {

    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 100;

    private static final Pattern MULTIPLE_SPACES =
            Pattern.compile("\\s+");

    private static final Pattern CONTAINS_LETTER_OR_NUMBER =
            Pattern.compile(".*[\\p{L}\\p{N}].*");

    private final String value;

    public ProductName(String value) {
        this.value = validateAndNormalize(value);
    }
    private static String validateAndNormalize(String value) {
        if (value == null) {
            throw new IllegalArgumentException(
                    "O nome do produto é obrigatório."
            );
        }

        String normalized = normalize(value);

        if (normalized.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do produto não pode estar vazio."
            );
        }

        if (normalized.length() < MIN_LENGTH
                || normalized.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(
                    "O nome do produto deve ter entre "
                            + MIN_LENGTH
                            + " e "
                            + MAX_LENGTH
                            + " caracteres."
            );
        }

        if (!CONTAINS_LETTER_OR_NUMBER
                .matcher(normalized)
                .matches()) {
            throw new IllegalArgumentException(
                    "O nome do produto deve conter ao menos uma letra ou número."
            );
        }

        return normalized;
    }

    private static String normalize(String value) {
        String trimmedValue = value.trim();

        return MULTIPLE_SPACES
                .matcher(trimmedValue)
                .replaceAll(" ");
    }

    public String getValue() {
        return value;
    }

}