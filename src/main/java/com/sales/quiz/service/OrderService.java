package com.sales.quiz.service;

import com.sales.quiz.converter.OrderDTO;
import com.sales.quiz.converter.ProductDTO;
import com.sales.quiz.model.Order;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface OrderService {

    @NotNull Iterable<Order> getAllOrders();

    Order create(@NotNull(message = "The order cannot be null.") @Valid OrderDTO order);

    Order update(@NotNull(message = "The order cannot be null.") @Valid OrderDTO order);

    Order getOrder(@Min(value = 1L, message = "Invalid order ID.") long id);
}
