package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.persistence.converter;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject.Money;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.math.BigDecimal;

@WritingConverter
public class MoneyWriteConverter implements Converter<Money, BigDecimal> {
    @Override
    public BigDecimal convert(Money source) {
        return source.value();
    }

}
