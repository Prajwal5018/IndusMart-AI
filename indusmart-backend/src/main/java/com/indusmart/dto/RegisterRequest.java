package com.indusmart.dto;

import com.indusmart.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "Full name is required")
    @Size(min = 3, max = 100,
            message = "Full name must be between 3 and 100 characters")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 150,
            message = "Email cannot exceed 150 characters")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Enter a valid 10-digit Indian mobile number"
    )
    private String phone;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 50,
            message = "Password must be between 8 and 50 characters")
    private String password;

    @NotNull(message = "Role is required")
    private Role role;

}