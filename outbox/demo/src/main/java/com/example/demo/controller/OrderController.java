package com.example.demo.controller;


import com.example.demo.common.OrderDTO;
import com.example.demo.entity.Order;
import com.example.demo.services.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO) throws JsonProcessingException {
        Order CreatedOrder=orderService.createOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(CreatedOrder);

    }
}
