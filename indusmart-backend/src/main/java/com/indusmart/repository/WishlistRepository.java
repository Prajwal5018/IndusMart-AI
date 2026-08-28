package com.indusmart.repository;

import com.indusmart.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    /**
     * Get all wishlist items of a buyer
     */
    List<Wishlist> findByBuyerIdOrderByAddedAtDesc(Long buyerId);

    /**
     * Find wishlist item by buyer and product
     */
    Optional<Wishlist> findByBuyerIdAndProductId(Long buyerId,
                                                 Long productId);

    /**
     * Check if product already exists in wishlist
     */
    boolean existsByBuyerIdAndProductId(Long buyerId,
                                        Long productId);

    /**
     * Delete wishlist item by buyer and product
     */
    void deleteByBuyerIdAndProductId(Long buyerId,
                                     Long productId);

    /**
     * Count wishlist items of buyer
     */
    long countByBuyerId(Long buyerId);

    /**
     * Delete all wishlist items of buyer
     */
    void deleteByBuyerId(Long buyerId);

    /**
     * Count how many users added this product to wishlist
     */
    long countByProductId(Long productId);

    /**
     * Get all wishlist entries of a product
     */
    List<Wishlist> findByProductId(Long productId);
}