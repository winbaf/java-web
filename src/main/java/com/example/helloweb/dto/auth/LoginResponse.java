package com.example.helloweb.dto.auth;

public record LoginResponse(
        String token,
        String tokenType,
        long expiresInSeconds
) {
}
