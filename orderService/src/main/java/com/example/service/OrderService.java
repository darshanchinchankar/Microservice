package com.example.service;

import com.example.dto.OrderResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final List<OrderResponse> orders = List.of(
            new OrderResponse(100L, 1L),
            new OrderResponse(101L, 2L),
            new OrderResponse(102L, 3L)
    );

    public OrderResponse getOrderById(Long orderId) {

        return orders.stream()
                .filter(order -> order.getOrderId().equals(orderId))
                .findFirst()
                .orElse(null);
    }
}