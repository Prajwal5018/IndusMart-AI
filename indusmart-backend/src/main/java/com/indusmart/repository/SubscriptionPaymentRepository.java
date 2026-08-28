package com.indusmart.repository;

import com.indusmart.entity.SubscriptionPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriptionPaymentRepository extends JpaRepository<SubscriptionPayment, Long> {

    /**
     * Get all payments made by a seller
     */
    List<SubscriptionPayment> findBySellerId(Long sellerId);

    /**
     * Find payment using Razorpay Payment ID
     */
    Optional<SubscriptionPayment> findByRazorpayPaymentId(String paymentId);

    /**
     * Find payment using Razorpay Order ID
     */
    Optional<SubscriptionPayment> findByRazorpayOrderId(String orderId);

    /**
     * Check whether Razorpay Payment ID already exists
     */
    boolean existsByRazorpayPaymentId(String paymentId);

    /**
     * Check whether Razorpay Order ID already exists
     */
    boolean existsByRazorpayOrderId(String orderId);

}