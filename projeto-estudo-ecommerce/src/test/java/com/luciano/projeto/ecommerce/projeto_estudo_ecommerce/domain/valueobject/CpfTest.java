package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.exception.InvalidCpfException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CpfTest {

    @Test
    void shouldThrowExceptionWhenCpfIsNull() {

        assertThrows(InvalidCpfException.class,
                () -> new Cpf(null)
        );

    }

    @Test
    void shouldThrowExceptionWhenCpfIsBlank() {

        assertThrows(InvalidCpfException.class,
                () -> new Cpf(" "));
    }

    @Test
    void shouldThrowExceptionWhenCpfContainsOnlyWhitespace() {

        assertThrows(InvalidCpfException.class,
                () -> new Cpf("  "));
    }

    @Test
    void shouldThrowExceptionWhenCpfHasInvalidFormat() {
        assertThrows(InvalidCpfException.class,
                ()-> new Cpf("a2345678909"));


    }

    @Test
    void shouldStoreCpfWithoutMask() {

        Cpf cpf = new Cpf("123.456.789-09");
        Cpf cpfTwo = new Cpf("12345678909");

        assertEquals("12345678909", cpf.value());
        assertEquals("12345678909", cpfTwo.value());

    }

    @Test
    void shouldNormalizeCpf() {
        Cpf cpf = new Cpf("     123.456.789-09        ");
        assertEquals("12345678909", cpf.value());
    }

//    @Test
//    void shouldThrowExceptionWhenCpfHasWrongSecondCheckDigit() {
//        assertThrows(
//                InvalidCpfException.class,
//                ()-> new Cpf("123.456.789-08")
//        );
//    }
}
