package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.persistence.converter;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject.Money;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.math.BigDecimal;

@ReadingConverter
public class MoneyReadConverter implements Converter<BigDecimal, Money> {

    @Override
    public Money convert(BigDecimal source) {
        return new Money(source);
    }
}
