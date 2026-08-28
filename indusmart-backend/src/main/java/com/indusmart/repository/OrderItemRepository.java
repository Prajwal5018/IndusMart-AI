package com.indusmart.repository;

import com.indusmart.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    /**
     * Get all items of an order
     */
    List<OrderItem> findByOrderId(Long orderId);

    /**
     * Get all ordered products of a company (Seller Dashboard)
     */
    List<OrderItem> findByCompanyId(Long companyId);

    /**
     * Get all ordered products of a company by status
     */
    List<OrderItem> findByCompanyIdAndStatus(Long companyId,
                                             String status);

    /**
     * Get all ordered products by product
     */
    List<OrderItem> findByProductId(Long productId);

    /**
     * Count number of orders for a product
     */
    long countByProductId(Long productId);

    /**
     * Count number of orders of a seller
     */
    long countByCompanyId(Long companyId);

}