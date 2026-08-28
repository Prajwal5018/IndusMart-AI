package com.indusmart.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Invoice Number
     */
    @Column(nullable = false, unique = true)
    private String invoiceNumber;

    /**
     * Order
     */
    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    /**
     * Buyer
     */
    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private User buyer;

    /**
     * Total Amount
     */
    @Column(nullable = false)
    private BigDecimal totalAmount;

    /**
     * GST Percentage
     */
    @Builder.Default
    private Double gstPercentage = 18.0;

    /**
     * GST Amount
     */
    @Column(nullable = false)
    private BigDecimal gstAmount;

    /**
     * Final Amount
     */
    @Column(nullable = false)
    private BigDecimal finalAmount;

    /**
     * Invoice Date
     */
    @Builder.Default
    private LocalDateTime invoiceDate = LocalDateTime.now();

    /**
     * Payment Status
     */
    @Column(nullable = false)
    private String paymentStatus;

    /**
     * Download URL
     */
    private String invoiceUrl;

}