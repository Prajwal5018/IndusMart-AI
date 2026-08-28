package com.indusmart.repository;

import com.indusmart.entity.SellerSubscription;
import com.indusmart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerSubscriptionRepository extends JpaRepository<SellerSubscription, Long> {

    /**
     * Find active subscription of a seller
     */
    Optional<SellerSubscription> findBySeller(User seller);

    /**
     * Find active subscription using Seller ID
     */
    Optional<SellerSubscription> findBySellerId(Long sellerId);

    /**
     * Check whether seller already has a subscription
     */
    boolean existsBySeller(User seller);

    /**
     * Check whether seller already has a subscription using seller ID
     */
    boolean existsBySellerId(Long sellerId);

}