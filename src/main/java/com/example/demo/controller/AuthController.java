package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            User user = userService.register(request.username, request.email, request.password);
            String token = jwtUtil.generateToken(user.getUsername());

            return ResponseEntity.ok(new AuthResponse(token, user.getUsername()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<User> user = userService.authenticate(request.username, request.password);

        if (user.isPresent()) {
            String token = jwtUtil.generateToken(user.get().getUsername());
            return ResponseEntity.ok(new AuthResponse(token, user.get().getUsername()));
        }

        return ResponseEntity.status(401).body("Invalid credentials");
    }

    static class RegisterRequest {
        public String username;
        public String email;
        public String password;
    }

    static class LoginRequest {
        public String username;
        public String password;
    }

    static class AuthResponse {
        public String token;
        public String username;

        public AuthResponse(String token, String username) {
            this.token = token;
            this.username = username;
        }
    }
}