package com.indusmart.controller;

import com.indusmart.dto.LoginRequest;
import com.indusmart.dto.LoginResponse;
import com.indusmart.dto.RegisterRequest;
import com.indusmart.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * User Registration
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterRequest request) {

        String response = authService.register(request);

        if ("User Registered Successfully".equals(response)) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);
        }

        return ResponseEntity
                .badRequest()
                .body(response);
    }

    /**
     * User Login
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response = authService.login(request);

        if (response.getToken() == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(response);
        }

        return ResponseEntity.ok(response);
    }

}