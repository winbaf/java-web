package com.example.helloweb.service;

import com.example.helloweb.dto.auth.LoginRequest;
import com.example.helloweb.dto.auth.LoginResponse;
import com.example.helloweb.dto.auth.RegisterRequest;
import com.example.helloweb.entity.User;
import com.example.helloweb.repository.UserRepository;
import com.example.helloweb.security.JwtService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final String expectedUsername;
    private final String expectedPassword;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            @Value("${app.auth.username}") String expectedUsername,
            @Value("${app.auth.password}") String expectedPassword,
            JwtService jwtService,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.expectedUsername = expectedUsername;
        this.expectedPassword = expectedPassword;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    void createDefaultUserIfMissing() {
        if (!userRepository.existsByUsername(expectedUsername)) {
            userRepository.save(new User(expectedUsername, passwordEncoder.encode(expectedPassword)));
        }
    }

    public Optional<LoginResponse> login(LoginRequest request) {
        return userRepository.findByUsername(request.username())
                .filter(user -> passwordEncoder.matches(request.password(), user.getPassword()))
                .map(user -> createLoginResponse(user.getUsername()));
    }

    public Optional<LoginResponse> register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            return Optional.empty();
        }

        User user = userRepository.save(new User(
                request.username(),
                passwordEncoder.encode(request.password())
        ));

        return Optional.of(createLoginResponse(user.getUsername()));
    }

    private LoginResponse createLoginResponse(String username) {
        return new LoginResponse(
                jwtService.generateToken(username),
                "Bearer",
                jwtService.getExpirationSeconds()
        );
    }
}
