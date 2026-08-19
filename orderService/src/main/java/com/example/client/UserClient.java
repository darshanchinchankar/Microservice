package com.example.client;

import com.example.dto.UserResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class UserClient {

    private final RestClient restClient;

    public UserClient(
            RestClient.Builder builder,
            @Value("${user.service.base-url}") String userServiceBaseUrl
    ) {
        this.restClient = builder
                .baseUrl(userServiceBaseUrl)
                .build();
    }

    @CircuitBreaker(
            name = "userService",
            fallbackMethod = "userFallback"
    )
    public UserResponse getUser(Long userId) {

        return restClient.get()
                .uri("/api/users/{id}", userId)
                .retrieve()
                .body(UserResponse.class);
    }

    public UserResponse userFallback(Long userId, Throwable throwable) {

        System.out.println("=================================");
        System.out.println("USER SERVICE IS DOWN");
        System.out.println("CIRCUIT BREAKER FALLBACK TRIGGERED");
        System.out.println("Reason: " + throwable.getMessage());
        System.out.println("=================================");

        return new UserResponse(
                userId,
                "Unknown",
                "User Service unavailable"
        );
    }
}