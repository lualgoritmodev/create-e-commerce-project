package com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.infra.persistence.converter;

import com.luciano.projeto.ecommerce.projeto_estudo_ecommerce.domain.valueobject.ProductName;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class ProductNameWriteConverter implements Converter<ProductName, String> {

    @Override
    public String convert(ProductName source) { return source.value(); }

}
