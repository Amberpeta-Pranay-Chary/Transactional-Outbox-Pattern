package com.example.demo.common.mapper;

import com.example.demo.entity.Order;
import com.example.demo.entity.Outbox;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OrderEntityToOutboxPattern {
    public Outbox map(Order order) throws JsonProcessingException {
//       return Outbox.builder().build();
        Outbox outbox = new Outbox();
        outbox.setAggregateId(order.getCustomerId().toString());
        outbox.setPayload(new ObjectMapper().writeValueAsString(order));
        outbox.setCreatedAt(new Date());
        outbox.setProcessed(false);
        return outbox;
    }
}
