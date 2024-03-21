package com.sales.quiz.converter;


import com.sales.quiz.model.OrderProduct;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderProductMapper {
    OrderProductMapper INSTANCE = Mappers.getMapper(OrderProductMapper.class);

    OrderProductDTO OrderProductToOrderProductDTO(OrderProduct orderProduct);

    OrderProduct OrderProductDTOToOrderProduct(OrderProductDTO orderProductDTO);
}
