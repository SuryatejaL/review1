package com.cravedash.gateway.controller;

import com.cravedash.gateway.dto.LoginRequest;
import com.cravedash.gateway.service.JwtService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request) {

        // Demo credentials for JWT generation
        if ("admin".equals(request.getUsername())
                && "admin123".equals(request.getPassword())) {

            String token = jwtService.generateToken(
                    request.getUsername()
            );

            Map<String, Object> response = new HashMap<>();

            response.put("message", "Login successful");
            response.put("token", token);
            response.put("tokenType", "Bearer");

            return ResponseEntity.ok(response);
        }

        return ResponseEntity
                .status(401)
                .body("Invalid username or password");
    }
}