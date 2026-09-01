package com.example.helloweb.service;

import com.example.helloweb.dto.auth.LoginRequest;
import com.example.helloweb.dto.auth.LoginResponse;
import com.example.helloweb.security.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final String expectedUsername;
    private final String expectedPassword;
    private final JwtService jwtService;

    public AuthService(
            @Value("${app.auth.username}") String expectedUsername,
            @Value("${app.auth.password}") String expectedPassword,
            JwtService jwtService
    ) {
        this.expectedUsername = expectedUsername;
        this.expectedPassword = expectedPassword;
        this.jwtService = jwtService;
    }

    public Optional<LoginResponse> login(LoginRequest request) {
        if (!expectedUsername.equals(request.username()) || !expectedPassword.equals(request.password())) {
            return Optional.empty();
        }

        return Optional.of(new LoginResponse(
                jwtService.generateToken(request.username()),
                "Bearer",
                jwtService.getExpirationSeconds()
        ));
    }
}
