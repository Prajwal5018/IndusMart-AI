package com.indusmart.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRecommendationDTO {

    /**
     * Product ID
     */
    private Long productId;

    /**
     * Product Name
     */
    private String productName;

    /**
     * Category
     */
    private String category;

    /**
     * Brand
     */
    private String brand;

    /**
     * Price
     */
    private BigDecimal purchasePrice;

    /**
     * Image
     */
    private String imageUrl;

    /**
     * Average Rating
     */
    private Double averageRating;

    /**
     * Wishlist Count
     */
    private Long wishlistCount;

    /**
     * Recommendation Score
     */
    private Double recommendationScore;

}