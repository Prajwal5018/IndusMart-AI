package com.indusmart.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CompanyRequest {

    /**
     * Company Name
     */
    @NotBlank(message = "Company name is required")
    @Size(min = 3, max = 150,
            message = "Company name must be between 3 and 150 characters")
    private String companyName;

    /**
     * GST Number
     * Example: 29ABCDE1234F1Z5
     */
    @NotBlank(message = "GST number is required")
    @Pattern(
            regexp = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[A-Z0-9]{3}$",
            message = "Invalid GST number"
    )
    private String gstNumber;

    /**
     * PAN Number
     * Example: ABCDE1234F
     */
    @NotBlank(message = "PAN number is required")
    @Pattern(
            regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$",
            message = "Invalid PAN number"
    )
    private String panNumber;

    /**
     * Company Email
     */
    @NotBlank(message = "Company email is required")
    @Email(message = "Invalid email format")
    @Size(max = 150,
            message = "Email cannot exceed 150 characters")
    private String email;

    /**
     * Company Phone
     */
    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Enter a valid 10-digit Indian mobile number"
    )
    private String phone;

    /**
     * Website
     */
    @Size(max = 255,
            message = "Website URL cannot exceed 255 characters")
    private String website;

    /**
     * Address
     */
    @NotBlank(message = "Address is required")
    @Size(max = 500,
            message = "Address cannot exceed 500 characters")
    private String address;

    /**
     * City
     */
    @NotBlank(message = "City is required")
    @Size(max = 100,
            message = "City cannot exceed 100 characters")
    private String city;

    /**
     * State
     */
    @NotBlank(message = "State is required")
    @Size(max = 100,
            message = "State cannot exceed 100 characters")
    private String state;

    /**
     * Country
     */
    @NotBlank(message = "Country is required")
    @Size(max = 100,
            message = "Country cannot exceed 100 characters")
    private String country;

    /**
     * PIN Code
     */
    @NotBlank(message = "PIN code is required")
    @Pattern(
            regexp = "^[1-9][0-9]{5}$",
            message = "Invalid PIN code"
    )
    private String pincode;

    /**
     * Company Description
     */
    @Size(max = 2000,
            message = "Description cannot exceed 2000 characters")
    private String description;

    /**
     * Company Logo URL
     */
    @Size(max = 1000,
            message = "Logo URL cannot exceed 1000 characters")
    private String logoUrl;

    /**
     * Seller (Owner) ID
     */
    @NotNull(message = "Owner Id is required")
    @Positive(message = "Owner Id must be positive")
    private Long ownerId;

}