package com.indusmart.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerDashboardDTO {

    /**
     * Company Details
     */
    private Long companyId;

    private String companyName;

    /**
     * Product Statistics
     */
    private Long totalProducts;

    private Long availableProducts;

    private Long outOfStockProducts;

    /**
     * Order Statistics
     */
    private Long totalOrders;

    private Long pendingOrders;

    private Long confirmedOrders;

    private Long shippedOrders;

    private Long deliveredOrders;

    private Long cancelledOrders;

    /**
     * Revenue
     */
    private BigDecimal totalRevenue;

    /**
     * Reviews
     */
    private Double averageRating;

    private Long totalReviews;

    /**
     * Wishlist
     */
    private Long wishlistCount;

    /**
     * Inventory
     */
    private Long lowStockProducts;

}