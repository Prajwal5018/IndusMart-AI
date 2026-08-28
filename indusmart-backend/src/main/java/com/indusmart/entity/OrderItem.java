package com.indusmart.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Parent Order
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    /**
     * Purchased/Rented Product
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    /**
     * Seller Company
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    /**
     * Quantity
     */
    @Column(nullable = false)
    private Integer quantity;

    /**
     * Price at the time of order
     */
    @Column(nullable = false)
    private BigDecimal unitPrice;

    /**
     * Total Price
     */
    @Column(nullable = false)
    private BigDecimal totalPrice;

    /**
     * Purchase or Rental
     */
    @Column(nullable = false)
    private boolean rental;

    /**
     * Rental Duration (Days)
     */
    @Builder.Default
    private Integer rentalDays = 0;

    /**
     * Current Status of this Item
     *
     * PENDING
     * CONFIRMED
     * PROCESSING
     * SHIPPED
     * DELIVERED
     * CANCELLED
     */
    @Column(nullable = false)
    @Builder.Default
    private String status = "PENDING";
}