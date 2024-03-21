package com.sales.quiz.controller;


import com.sales.quiz.converter.OrderDTO;
import com.sales.quiz.converter.ProductDTO;
import com.sales.quiz.model.Order;
import com.sales.quiz.service.OrderService;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/orders")
@Slf4j
public class OrderController {

   private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @NotNull Iterable<Order> list() {
        return this.orderService.getAllOrders();
    }

    @GetMapping(value = {  "/{clientId}" })
    public Order getOrder(@PathVariable Long clientId) {
        return orderService.getOrder(clientId);
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderDTO form) {

       Order order = this.orderService.create(form);
        String uri = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/orders/{id}")
                .buildAndExpand(order.getId())
                .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);
        // log
        log.info("New Order => " + order.toString());
        return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<Order> edit(@RequestBody OrderDTO form) {

        Order order = this.orderService.update(form);
        String uri = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/orders/{id}")
                .buildAndExpand(order.getId())
                .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);
        // log
        log.info("Updated Order => " + order.toString());
        return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
    }
}
