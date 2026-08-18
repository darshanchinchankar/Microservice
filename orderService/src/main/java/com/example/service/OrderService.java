package com.example.service;

import com.example.client.PaymentClient;
import com.example.client.UserClient;
import com.example.dto.OrderResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final UserClient userClient;
    private final PaymentClient paymentClient;

    private final List<OrderResponse> orders = List.of(
            new OrderResponse(100L, 1L),
            new OrderResponse(101L, 2L),
            new OrderResponse(102L, 3L)
    );

    public OrderService(
            UserClient userClient,
            PaymentClient paymentClient
    ) {
        this.userClient = userClient;
        this.paymentClient = paymentClient;
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
        String user = userClient.getUser(order.getUserId());

        System.out.println("User response: " + user);

        // Call Payment Service
        String payment = paymentClient.getPayment(orderId);

        System.out.println("Payment response: " + payment);

        return order;
    }
}