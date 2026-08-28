package com.indusmart.service;

import com.indusmart.entity.Order;
import com.indusmart.entity.OrderItem;
import com.indusmart.repository.OrderItemRepository;
import com.indusmart.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrderQueryService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderQueryService(OrderRepository orderRepository,
                             OrderItemRepository orderItemRepository) {

        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    /**
     * Buyer Order History
     */
    public List<Order> getOrdersByBuyer(Long buyerId) {

        return orderRepository
                .findByBuyerIdOrderByOrderDateDesc(buyerId);

    }

    /**
     * Get Order By ID
     */
    public Order getOrder(Long orderId) {

        Optional<Order> optionalOrder =
                orderRepository.findById(orderId);

        return optionalOrder.orElse(null);

    }

    /**
     * Get Order Items
     */
    public List<OrderItem> getOrderItems(Long orderId) {

        return orderItemRepository.findByOrderId(orderId);

    }

    /**
     * Seller Orders
     */
    public List<OrderItem> getSellerOrders(Long companyId) {

        return orderItemRepository.findByCompanyId(companyId);

    }

    /**
     * Seller Pending Orders
     */
    public List<OrderItem> getSellerPendingOrders(Long companyId) {

        return orderItemRepository.findByCompanyIdAndStatus(
                companyId,
                "PENDING"
        );

    }

    /**
     * Total Orders Of Seller
     */
    public long totalOrders(Long companyId) {

        return orderItemRepository.countByCompanyId(companyId);

    }

    /**
     * Total Sales Of Seller
     */
    public BigDecimal totalSales(Long companyId) {

        List<OrderItem> items =
                orderItemRepository.findByCompanyId(companyId);

        BigDecimal total = BigDecimal.ZERO;

        for (OrderItem item : items) {

            if (!"CANCELLED".equals(item.getStatus())) {

                total = total.add(item.getTotalPrice());

            }

        }

        return total;

    }

    /**
     * Track Order
     */
    public String trackOrder(Long orderId) {

        Optional<Order> optionalOrder =
                orderRepository.findById(orderId);

        if (optionalOrder.isEmpty()) {

            return "Order Not Found";

        }

        return optionalOrder.get().getOrderStatus();

    }

}