package com.example.service;

import com.example.dto.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final List<UserResponse> users = List.of(
            new UserResponse(1L, "Darshan", "darshan@example.com"),
            new UserResponse(2L, "Rahul", "rahul@example.com"),
            new UserResponse(3L, "Priya", "priya@example.com")
    );

    public UserResponse getUserById(Long id) {

        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}