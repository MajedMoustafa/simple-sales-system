package com.sales.quiz.converter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sales.quiz.model.Client;
import com.sales.quiz.model.OrderProduct;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDTO {

    private Long id;
    private String status;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreated;
    private ClientDTO client;
    private List<OrderProductDTO> orderProducts = new ArrayList<>();
}
