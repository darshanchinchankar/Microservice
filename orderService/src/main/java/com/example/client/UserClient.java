package com.example.client;

import com.example.dto.UserResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class UserClient {

    private final RestClient restClient;

    public UserClient(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("http://localhost:8081")
                .build();
    }

    public UserResponse getUserById(Long userId) {

        return restClient.get()
                .uri("/api/users/{id}", userId)
                .retrieve()
                .body(UserResponse.class);
    }
}