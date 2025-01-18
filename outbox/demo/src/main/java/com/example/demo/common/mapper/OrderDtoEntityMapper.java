package com.example.demo.common.mapper;

import com.example.demo.common.OrderDTO;
import com.example.demo.entity.Order;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OrderDtoEntityMapper {
    public Order mapToOrder(OrderDTO orderDTO)
    {

        Order order = new Order();
        order.setCustomerId(orderDTO.getCustomerId());
        order.setQuantity(orderDTO.getQuantity());
        order.setName(orderDTO.getName());
        order.setProductType(orderDTO.getProductType());
        order.setPrice(orderDTO.getPrice());
        order.setOrderDate(new Date());
        return order;
    }
}
