package com.indusmart.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "seller_subscriptions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Seller who purchased the subscription
     */
    @OneToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    /**
     * Selected Subscription Plan
     */
    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private SubscriptionPlan subscriptionPlan;

    /**
     * Subscription Start Date
     */
    @Column(nullable = false)
    private LocalDate startDate;

    /**
     * Subscription Expiry Date
     */
    @Column(nullable = false)
    private LocalDate expiryDate;

    /**
     * Number of products uploaded by seller
     */
    @Column(nullable = false)
    @Builder.Default
    private Integer productsUploaded = 0;

    /**
     * Subscription Status
     */
    @Column(nullable = false)
    @Builder.Default
    private boolean active = true;

    /**
     * Razorpay Payment ID
     */
    private String paymentId;

    /**
     * Razorpay Order ID
     */
    private String orderId;

    /**
     * Razorpay Signature
     */
    private String paymentSignature;
}