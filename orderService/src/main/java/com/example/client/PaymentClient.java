package com.example.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class PaymentClient {
    private final RestClient restClient;

    public PaymentClient(RestClient.Builder builder) {
        this.restClient = builder.build();
    }

    @CircuitBreaker(name = "paymentService", fallbackMethod = "paymentFallback")
    public String getPayment(Long orderId) {
        return restClient.get().uri("http://localhost:8084/api/payments/" + orderId).retrieve().body(String.class);
    }

    public String paymentFallback(Long orderId, Throwable throwable) {
        System.out.println("Payment fallback triggered: " + throwable.getMessage());
        return "Payment Service is currently unavailable for order: " + orderId;
    }
}