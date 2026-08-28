package com.indusmart.repository;

import com.indusmart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    /**
     * Get all cart items of a buyer
     */
    List<Cart> findByBuyerId(Long buyerId);

    /**
     * Find a cart item by buyer and product
     */
    Optional<Cart> findByBuyerIdAndProductId(Long buyerId, Long productId);

    /**
     * Check if product already exists in buyer's cart
     */
    boolean existsByBuyerIdAndProductId(Long buyerId, Long productId);

    /**
     * Remove all cart items of a buyer
     */
    void deleteByBuyerId(Long buyerId);

    /**
     * Count total items in buyer's cart
     */
    long countByBuyerId(Long buyerId);
}