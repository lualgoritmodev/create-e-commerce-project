package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.persistence.converter;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject.ProductName;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class ProductNameReadConverter implements Converter<String, ProductName> {
    @Override
    public ProductName convert(String source) {
        return new ProductName(source);
    }
}
