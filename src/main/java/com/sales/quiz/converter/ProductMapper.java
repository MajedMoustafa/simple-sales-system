package com.sales.quiz.converter;

import com.sales.quiz.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO ProductToProductDTO(Product product);

    Product ProductDTOToProduct(ProductDTO productDTO);

}
