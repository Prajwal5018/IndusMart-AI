package com.indusmart.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Product to which this image belongs
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    /**
     * Image URL
     */
    @Column(nullable = false)
    private String imageUrl;

    /**
     * Image display order
     * 1 = Thumbnail
     * 2 = Second Image
     * 3 = Third Image
     * etc.
     */
    @Column(nullable = false)
    private Integer displayOrder;

    /**
     * Primary Image
     */
    @Column(nullable = false)
    @Builder.Default
    private boolean primaryImage = false;
}