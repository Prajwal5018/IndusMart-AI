package com.indusmart.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Product Name
    @Column(nullable = false)
    private String productName;

    // Product Description
    @Column(length = 3000)
    private String description;

    // Buy Price
    private BigDecimal purchasePrice;

    // Rent Price Per Day
    private BigDecimal rentalPricePerDay;

    // Quantity Available
    private Integer quantity;

    // Category
    private String category;

    // Brand
    private String brand;

    // Model Number
    private String modelNumber;

    // Image URL
    private String imageUrl;

    // Available for Rent?
    private boolean availableForRent;

    // Available for Purchase?
    private boolean availableForSale;

    // Company owning this product
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}