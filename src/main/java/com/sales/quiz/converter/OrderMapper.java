package com.sales.quiz.converter;

import com.sales.quiz.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order OrderDTOToOrder(OrderDTO orderDTO);

    OrderDTO OrderToOrderDTO(Order order);
}
