package com.indusmart.repository;

import com.indusmart.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Get all orders of a buyer
     */
    List<Order> findByBuyerIdOrderByOrderDateDesc(Long buyerId);

    /**
     * Find order by Order Number
     */
    Optional<Order> findByOrderNumber(String orderNumber);

    /**
     * Find orders by Order Status
     */
    List<Order> findByOrderStatus(String orderStatus);

    /**
     * Find orders by Payment Status
     */
    List<Order> findByPaymentStatus(String paymentStatus);

    /**
     * Check if Order Number already exists
     */
    boolean existsByOrderNumber(String orderNumber);
}