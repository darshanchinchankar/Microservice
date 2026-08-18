package com.example.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class UserClient {

    private final RestClient restClient;

    public UserClient(RestClient.Builder builder) {
        this.restClient = builder.build();
    }

    @CircuitBreaker(
            name = "userService",
            fallbackMethod = "userFallback"
    )
    public String getUser(Long userId) {

        return restClient.get()
                .uri("http://localhost:8081/api/users/" + userId)
                .retrieve()
                .body(String.class);
    }

    public String userFallback(
            Long userId,
            Throwable throwable
    ) {

        System.out.println(
                "User fallback triggered: "
                        + throwable.getMessage()
        );

        return "User Service is currently unavailable for user: "
                + userId;
    }
}
