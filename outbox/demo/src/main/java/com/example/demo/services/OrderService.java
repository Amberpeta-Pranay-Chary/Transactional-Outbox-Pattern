package com.example.demo.services;

import com.example.demo.common.OrderDTO;
import com.example.demo.common.mapper.OrderDtoEntityMapper;
import com.example.demo.common.mapper.OrderEntityToOutboxPattern;
import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.OutboxRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderDtoEntityMapper orderDtoEntityMapper;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderEntityToOutboxPattern orderEntityToOutboxPattern;

    @Autowired
    OutboxRepository outboxRepository;

    @Transactional
    public Order createOrder(OrderDTO orderDTO) throws JsonProcessingException {
        Order order= orderDtoEntityMapper.mapToOrder(orderDTO);
        order=orderRepository.save(order);
        outboxRepository.save(orderEntityToOutboxPattern.map(order));
        return order;
    }
}
