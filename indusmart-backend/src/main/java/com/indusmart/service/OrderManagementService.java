package com.indusmart.service;

import com.indusmart.entity.Order;
import com.indusmart.entity.OrderItem;
import com.indusmart.entity.Product;
import com.indusmart.repository.OrderItemRepository;
import com.indusmart.repository.OrderRepository;
import com.indusmart.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderManagementService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    public OrderManagementService(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            ProductRepository productRepository) {

        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }

    /**
     * Cancel Order
     */
    public String cancelOrder(Long orderId) {

        Optional<Order> optionalOrder =
                orderRepository.findById(orderId);

        if (optionalOrder.isEmpty()) {
            return "Order Not Found";
        }

        Order order = optionalOrder.get();

        if ("DELIVERED".equals(order.getOrderStatus())) {
            return "Delivered Order Cannot Be Cancelled";
        }

        if ("CANCELLED".equals(order.getOrderStatus())) {
            return "Order Already Cancelled";
        }

        List<OrderItem> items =
                orderItemRepository.findByOrderId(orderId);

        for (OrderItem item : items) {

            item.setStatus("CANCELLED");

            orderItemRepository.save(item);

            // Restore Stock only for Purchase Orders
            if (!item.isRental()) {

                Product product = item.getProduct();

                product.setQuantity(
                        product.getQuantity() + item.getQuantity()
                );

                productRepository.save(product);
            }
        }

        order.setOrderStatus("CANCELLED");

        orderRepository.save(order);

        return "Order Cancelled Successfully";
    }

    /**
     * Update Order Status
     */
    public String updateOrderStatus(
            Long orderId,
            String status) {

        Optional<Order> optionalOrder =
                orderRepository.findById(orderId);

        if (optionalOrder.isEmpty()) {
            return "Order Not Found";
        }

        Order order = optionalOrder.get();

        order.setOrderStatus(status);

        orderRepository.save(order);

        List<OrderItem> items =
                orderItemRepository.findByOrderId(orderId);

        for (OrderItem item : items) {

            item.setStatus(status);

            orderItemRepository.save(item);
        }

        return "Order Status Updated Successfully";
    }

    /**
     * Update Payment Status
     */
    public String updatePaymentStatus(
            Long orderId,
            String paymentStatus) {

        Optional<Order> optionalOrder =
                orderRepository.findById(orderId);

        if (optionalOrder.isEmpty()) {
            return "Order Not Found";
        }

        Order order = optionalOrder.get();

        order.setPaymentStatus(paymentStatus);

        orderRepository.save(order);

        return "Payment Status Updated Successfully";
    }

    /**
     * Confirm Payment
     */
    public String confirmPayment(
            Long orderId,
            String razorpayOrderId,
            String razorpayPaymentId,
            String razorpaySignature) {

        Optional<Order> optionalOrder =
                orderRepository.findById(orderId);

        if (optionalOrder.isEmpty()) {
            return "Order Not Found";
        }

        Order order = optionalOrder.get();

        order.setPaymentStatus("SUCCESS");
        order.setOrderStatus("CONFIRMED");

        order.setRazorpayOrderId(razorpayOrderId);
        order.setRazorpayPaymentId(razorpayPaymentId);
        order.setRazorpaySignature(razorpaySignature);

        orderRepository.save(order);

        return "Payment Confirmed Successfully";
    }

    /**
     * Refund Order
     */
    public String refundOrder(Long orderId) {

        Optional<Order> optionalOrder =
                orderRepository.findById(orderId);

        if (optionalOrder.isEmpty()) {
            return "Order Not Found";
        }

        Order order = optionalOrder.get();

        order.setPaymentStatus("REFUNDED");

        orderRepository.save(order);

        return "Refund Initiated Successfully";
    }

}