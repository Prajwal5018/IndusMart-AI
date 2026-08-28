package com.indusmart.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Buyer
     */
    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private User buyer;

    /**
     * Order Number
     */
    @Column(nullable = false, unique = true)
    private String orderNumber;

    /**
     * Grand Total
     */
    @Column(nullable = false)
    private BigDecimal totalAmount;

    /**
     * PURCHASE or RENTAL
     */
    @Column(nullable = false)
    private String orderType;

    /**
     * Order Status
     *
     * PENDING
     * CONFIRMED
     * PROCESSING
     * SHIPPED
     * DELIVERED
     * CANCELLED
     */
    @Column(nullable = false)
    private String orderStatus;

    /**
     * Payment Status
     *
     * PENDING
     * SUCCESS
     * FAILED
     * REFUNDED
     */
    @Column(nullable = false)
    private String paymentStatus;

    /**
     * Shipping Address
     */
    @Column(length = 1000)
    private String shippingAddress;

    /**
     * Order Date
     */
    @Column(nullable = false)
    private LocalDateTime orderDate;

    /**
     * Expected Delivery
     */
    private LocalDateTime expectedDelivery;

    /**
     * Razorpay Order ID
     */
    private String razorpayOrderId;

    /**
     * Razorpay Payment ID
     */
    private String razorpayPaymentId;

    /**
     * Razorpay Signature
     */
    private String razorpaySignature;

}