package com.indusmart.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecommendationDTO {

    /**
     * Product Details
     */
    private Long productId;

    private String productName;

    private String brand;

    private String category;

    /**
     * Pricing
     */
    private BigDecimal purchasePrice;

    private BigDecimal rentalPricePerDay;

    /**
     * Product Image
     */
    private String imageUrl;

    /**
     * Seller
     */
    private String companyName;

    /**
     * Recommendation Statistics
     */
    private Double averageRating;

    private Long totalReviews;

    private Long wishlistCount;

    private Long orderCount;

    /**
     * AI Score (0–100)
     */
    private Double recommendationScore;

}