package com.indusmart.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WishlistRequest {

    /**
     * Buyer ID
     */
    @NotNull(message = "Buyer ID is required")
    private Long buyerId;

    /**
     * Product ID
     */
    @NotNull(message = "Product ID is required")
    private Long productId;

}