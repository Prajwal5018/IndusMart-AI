package com.indusmart.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "subscription_payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Seller who made the payment
     */
    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    /**
     * Purchased Subscription Plan
     */
    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private SubscriptionPlan subscriptionPlan;

    /**
     * Amount Paid
     */
    @Column(nullable = false)
    private BigDecimal amount;

    /**
     * Razorpay Order ID
     */
    @Column(unique = true)
    private String razorpayOrderId;

    /**
     * Razorpay Payment ID
     */
    @Column(unique = true)
    private String razorpayPaymentId;

    /**
     * Razorpay Signature
     */
    private String razorpaySignature;

    /**
     * Payment Method
     * Example:
     * UPI
     * CARD
     * NETBANKING
     * WALLET
     */
    private String paymentMethod;

    /**
     * Payment Status
     * SUCCESS
     * FAILED
     * PENDING
     * REFUNDED
     */
    @Column(nullable = false)
    private String paymentStatus;

    /**
     * Payment Date & Time
     */
    @Column(nullable = false)
    private LocalDateTime paymentDate;

    /**
     * Invoice Number
     */
    @Column(unique = true)
    private String invoiceNumber;
}