package com.indusmart.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminDashboardDTO {

    /**
     * Users
     */
    private Long totalUsers;

    /**
     * Companies
     */
    private Long totalCompanies;

    /**
     * Products
     */
    private Long totalProducts;

    /**
     * Orders
     */
    private Long totalOrders;

    /**
     * Revenue
     */
    private BigDecimal totalRevenue;

    /**
     * Reviews
     */
    private Long totalReviews;

    /**
     * Wishlist
     */
    private Long totalWishlistItems;

    /**
     * Active Subscriptions
     */
    private Long activeSubscriptions;

}