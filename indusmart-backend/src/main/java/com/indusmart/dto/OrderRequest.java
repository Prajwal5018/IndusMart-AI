package com.indusmart.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {

    /**
     * Buyer ID
     */
    @NotNull(message = "Buyer ID is required")
    @Positive(message = "Buyer ID must be positive")
    private Long buyerId;

    /**
     * Product ID
     */
    @NotNull(message = "Product ID is required")
    @Positive(message = "Product ID must be positive")
    private Long productId;

    /**
     * Quantity
     */
    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be greater than zero")
    private Integer quantity;

    /**
     * Purchase or Rental
     */
    private boolean rental;

    /**
     * Rental Start Date
     */
    @FutureOrPresent(message = "Rental start date cannot be in the past")
    private LocalDate rentalStartDate;

    /**
     * Rental End Date
     */
    @FutureOrPresent(message = "Rental end date cannot be in the past")
    private LocalDate rentalEndDate;

}