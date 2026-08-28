package com.indusmart.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewRequest {

    @NotNull(message = "Buyer ID is required")
    @Positive(message = "Buyer ID must be positive")
    private Long buyerId;

    @NotNull(message = "Product ID is required")
    @Positive(message = "Product ID must be positive")
    private Long productId;

    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be between 1 and 5")
    @Max(value = 5, message = "Rating must be between 1 and 5")
    private Integer rating;

    @NotBlank(message = "Review cannot be empty")
    @Size(min = 5, max = 1000,
            message = "Review must be between 5 and 1000 characters")
    private String review;

}