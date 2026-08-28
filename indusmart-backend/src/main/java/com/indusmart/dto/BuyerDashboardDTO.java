package com.indusmart.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuyerDashboardDTO {

    /**
     * Buyer Details
     */
    private Long buyerId;

    private String buyerName;

    private String email;

    /**
     * Cart Statistics
     */
    private Long cartItems;

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
     * Wishlist
     */
    private Long wishlistItems;

    /**
     * Reviews
     */
    private Long reviewsWritten;

    /**
     * Total Amount Spent
     */
    private BigDecimal totalSpent;

}