package com.indusmart.repository;

import com.indusmart.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    /**
     * Get all reviews of a product
     */
    List<Review> findByProductIdOrderByReviewedAtDesc(Long productId);

    /**
     * Get all reviews written by a buyer
     */
    List<Review> findByBuyerIdOrderByReviewedAtDesc(Long buyerId);

    /**
     * Check whether buyer already reviewed product
     */
    boolean existsByBuyerIdAndProductId(Long buyerId,
                                        Long productId);

    /**
     * Find review of buyer for a product
     */
    Optional<Review> findByBuyerIdAndProductId(Long buyerId,
                                               Long productId);

    /**
     * Delete review
     */
    void deleteByBuyerIdAndProductId(Long buyerId,
                                     Long productId);

    /**
     * Total reviews of a product
     */
    long countByProductId(Long productId);

    /**
     * Average Rating
     */
    @Query("""
            SELECT AVG(r.rating)
            FROM Review r
            WHERE r.product.id = :productId
            """)
    Double getAverageRating(Long productId);

    /**
     * Total 5-Star Ratings
     */
    long countByProductIdAndRating(Long productId, Integer rating);

}