package com.sales.quiz.service;

import com.sales.quiz.model.OrderProduct;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface OrderProductService {

    OrderProduct create(@NotNull(message = "The products for order cannot be null.") @Valid OrderProduct orderProduct);

}
