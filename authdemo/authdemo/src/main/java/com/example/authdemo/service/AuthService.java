package com.example.authdemo.service;

import com.example.authdemo.dto.LoginRequest;
import com.example.authdemo.dto.RegisterRequest;
import com.example.authdemo.dto.TokenResponse;
import com.example.authdemo.model.User;
import com.example.authdemo.repository.UserStorage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserStorage userStorage;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthService(UserStorage userStorage,
                     PasswordEncoder passwordEncoder,
                     TokenService tokenService) {
        this.userStorage = userStorage;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public TokenResponse register(RegisterRequest registerRequest) {
        if (userStorage.existsByUsername(registerRequest.username())) {
            return new TokenResponse(null, null, "Username already exists");
        }

        User user = new User();
        user.setUsername(registerRequest.username());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        user.setEmail(registerRequest.email());

        userStorage.save(user);
        return new TokenResponse(null, null, "Registration successful");
    }

    public TokenResponse login(LoginRequest loginRequest) {
        User user = userStorage.findByUsername(loginRequest.username());
        if (user == null || !passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            return new TokenResponse(null, null, "Invalid credentials");
        }
        
        String token = tokenService.generateToken(user.getUsername());
        return new TokenResponse(token, user.getUsername(), "Login successful");
    }
}