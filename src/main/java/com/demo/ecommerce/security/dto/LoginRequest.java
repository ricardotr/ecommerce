package com.demo.ecommerce.security.dto;

import lombok.Value;

@Value
public class LoginRequest {
    private final String username;
    private final String password;
    // Getters and setters
}