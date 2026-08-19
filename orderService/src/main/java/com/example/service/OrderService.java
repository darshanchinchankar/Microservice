package com.example.service;

import com.example.client.UserClient;
import com.example.dto.OrderResponse;
import com.example.dto.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final UserClient userClient;

    private final List<OrderResponse> orders = List.of(
            new OrderResponse(100L, 1L),
            new OrderResponse(101L, 2L),
            new OrderResponse(102L, 3L)
    );

    public OrderService(UserClient userClient) {
        this.userClient = userClient;
    }

    public OrderResponse getOrderById(Long orderId) {

        OrderResponse order = orders.stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .findFirst()
                .orElse(null);

        if (order == null) {
            return null;
        }

        // Call User Service
        UserResponse user = userClient.getUser(order.getUserId());

        System.out.println("User response: " + user.getName());

        return order;
    }
}