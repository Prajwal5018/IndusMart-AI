package com.indusmart.service;

import com.indusmart.dto.LoginRequest;
import com.indusmart.dto.LoginResponse;
import com.indusmart.dto.RegisterRequest;
import com.indusmart.entity.User;
import com.indusmart.repository.UserRepository;
import com.indusmart.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    /**
     * Register New User
     */
    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already registered";
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(user);

        return "User Registered Successfully";
    }

    /**
     * Login User
     */
    public LoginResponse login(LoginRequest request) {

        System.out.println("================================================");
        System.out.println("LOGIN EMAIL      : " + request.getEmail());

        boolean emailExists =
                userRepository.existsByEmail(request.getEmail());

        System.out.println("EMAIL EXISTS     : " + emailExists);

        Optional<User> optionalUser =
                userRepository.findByEmail(request.getEmail());

        System.out.println("USER FOUND       : " + optionalUser.isPresent());

        if (optionalUser.isEmpty()) {

            System.out.println("LOGIN FAILED -> USER NOT FOUND");

            return LoginResponse.builder()
                    .token(null)
                    .message("User not found")
                    .build();
        }

        User user = optionalUser.get();

        System.out.println("DATABASE EMAIL   : " + user.getEmail());
        System.out.println("DATABASE ROLE    : " + user.getRole());

        boolean passwordMatches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        System.out.println("PASSWORD MATCH   : " + passwordMatches);

        if (!passwordMatches) {

            System.out.println("LOGIN FAILED -> INVALID PASSWORD");

            return LoginResponse.builder()
                    .token(null)
                    .message("Invalid Password")
                    .build();
        }

        String token =
                jwtService.generateToken(user.getEmail());

        System.out.println("JWT GENERATED SUCCESSFULLY");
        System.out.println("================================================");

        return LoginResponse.builder()
                .token(token)
                .message("Login Successful")
                .build();
    }
}