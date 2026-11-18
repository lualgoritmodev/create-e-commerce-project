package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception.InvalidCpfException;

import java.util.regex.Pattern;

public record Cpf(String value) {

    private static final Pattern CPF_FORMAT =
            Pattern.compile("[0-9]{11}|[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}");

    public Cpf {
        validateRequired(value);
        String trimmedValue = value.strip();
        validateFormat(trimmedValue);

        value = normalize(trimmedValue);
    }

    private static void validateRequired(String value) {
        if (value == null || value.isBlank()) {
            throw new InvalidCpfException();
        }

    }

    private static void validateFormat(String value) {
        if(!CPF_FORMAT.matcher(value).matches()) {
            throw new InvalidCpfException();
        }

    }

    private static String normalize(String value) {
        return value.replace(".", "")
                .replace("-", "");
    }

    private static int calculateCheckDigit(String digits, int digitCount,
                                           int initialWeight) {
        int sum = 0;
        for(int index = 0; index < digitCount; index++) {
            int digit = Character.digit(digits.charAt(index), 10);
            int weight = initialWeight + index;
            sum += digit * weight;
        }
        int remainder = sum % 11;
        return remainder == 10 ? 0 : remainder;
    }
}
