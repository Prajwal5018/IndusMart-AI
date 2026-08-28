package com.indusmart.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "reviews",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"buyer_id", "product_id"})
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Buyer who reviewed
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", nullable = false)
    private User buyer;

    /**
     * Reviewed Product
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    /**
     * Rating (1 to 5)
     */
    @Column(nullable = false)
    private Integer rating;

    /**
     * Review Text
     */
    @Column(length = 3000)
    private String review;

    /**
     * Review Date
     */
    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime reviewedAt = LocalDateTime.now();

    /**
     * Last Updated Date
     */
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();
}