package com.indusmart.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {

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
     * Product
     */
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    /**
     * Quantity
     */
    @Column(nullable = false)
    @Builder.Default
    private Integer quantity = 1;

    /**
     * Purchase Price
     */
    @Column(nullable = false)
    private BigDecimal unitPrice;

    /**
     * Total Price
     */
    @Column(nullable = false)
    private BigDecimal totalPrice;

    /**
     * Rental or Purchase
     */
    @Column(nullable = false)
    private boolean rental;

    /**
     * Rental Days
     */
    @Builder.Default
    private Integer rentalDays = 0;
}