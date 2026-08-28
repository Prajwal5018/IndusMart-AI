package com.indusmart.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    /**
     * Product Name
     */
    @NotBlank(message = "Product name is required")
    @Size(min = 3, max = 200,
            message = "Product name must be between 3 and 200 characters")
    private String productName;

    /**
     * Product Description
     */
    @NotBlank(message = "Product description is required")
    @Size(min = 20, max = 3000,
            message = "Description must be between 20 and 3000 characters")
    private String description;

    /**
     * Purchase Price
     */
    @NotNull(message = "Purchase price is required")
    @DecimalMin(value = "0.0", inclusive = false,
            message = "Purchase price must be greater than zero")
    @Digits(integer = 10, fraction = 2,
            message = "Invalid purchase price")
    private BigDecimal purchasePrice;

    /**
     * Rental Price Per Day
     */
    @NotNull(message = "Rental price is required")
    @DecimalMin(value = "0.0", inclusive = false,
            message = "Rental price must be greater than zero")
    @Digits(integer = 10, fraction = 2,
            message = "Invalid rental price")
    private BigDecimal rentalPricePerDay;

    /**
     * Quantity
     */
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Max(value = 100000, message = "Quantity is too large")
    private Integer quantity;

    /**
     * Category
     */
    @NotBlank(message = "Category is required")
    @Size(max = 100,
            message = "Category cannot exceed 100 characters")
    private String category;

    /**
     * Brand
     */
    @NotBlank(message = "Brand is required")
    @Size(max = 100,
            message = "Brand cannot exceed 100 characters")
    private String brand;

    /**
     * Model Number
     */
    @NotBlank(message = "Model number is required")
    @Size(max = 100,
            message = "Model number cannot exceed 100 characters")
    private String modelNumber;

    /**
     * Product Image URL
     *
     * Optional because images can be uploaded later
     * through the Cloudinary upload API.
     */
    @Size(max = 1000,
            message = "Image URL cannot exceed 1000 characters")
    private String imageUrl;

    /**
     * Rental Availability
     */
    private boolean availableForRent;

    /**
     * Purchase Availability
     */
    private boolean availableForSale;

    /**
     * Company ID
     */
    @NotNull(message = "Company ID is required")
    @Positive(message = "Company ID must be positive")
    private Long companyId;

}