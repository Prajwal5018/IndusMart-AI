package com.indusmart.service;

import com.indusmart.dto.CheckoutResponseDTO;
import com.indusmart.entity.*;
import com.indusmart.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderCheckoutService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public OrderCheckoutService(
            CartRepository cartRepository,
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            ProductRepository productRepository,
            UserRepository userRepository) {

        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    /**
     * Checkout Cart
     */
    public CheckoutResponseDTO checkout(
            Long buyerId,
            String shippingAddress) {

        User buyer = userRepository.findById(buyerId)
                .orElseThrow(() ->
                        new RuntimeException("Buyer not found"));

        List<Cart> cartItems =
                cartRepository.findByBuyerId(buyerId);

        if (cartItems.isEmpty()) {

            throw new RuntimeException("Cart is Empty");

        }

        BigDecimal grandTotal = BigDecimal.ZERO;

        boolean rentalOrder = false;

        for (Cart cart : cartItems) {

            Product product = cart.getProduct();

            if (!cart.isRental()) {

                if (product.getQuantity() < cart.getQuantity()) {

                    throw new RuntimeException(
                            "Insufficient Stock for "
                                    + product.getProductName()
                    );

                }

            }

            grandTotal =
                    grandTotal.add(cart.getTotalPrice());

            if (cart.isRental()) {

                rentalOrder = true;

            }

        }

        String orderNumber =
                "IND" + System.currentTimeMillis();

        Order order = Order.builder()

                .buyer(buyer)

                .orderNumber(orderNumber)

                .totalAmount(grandTotal)

                .orderType(
                        rentalOrder
                                ? "RENTAL"
                                : "PURCHASE"
                )

                .orderStatus("PENDING")

                .paymentStatus("PENDING")

                .shippingAddress(shippingAddress)

                .orderDate(LocalDateTime.now())

                .expectedDelivery(
                        LocalDateTime.now().plusDays(7)
                )

                .build();

        orderRepository.save(order);

        for (Cart cart : cartItems) {

            Product product = cart.getProduct();

            OrderItem item = OrderItem.builder()

                    .order(order)

                    .product(product)

                    .company(product.getCompany())

                    .quantity(cart.getQuantity())

                    .unitPrice(cart.getUnitPrice())

                    .totalPrice(cart.getTotalPrice())

                    .rental(cart.isRental())

                    .rentalDays(cart.getRentalDays())

                    .status("PENDING")

                    .build();

            orderItemRepository.save(item);

            if (!cart.isRental()) {

                product.setQuantity(

                        product.getQuantity()

                                - cart.getQuantity()

                );

                productRepository.save(product);

            }

        }

        cartRepository.deleteByBuyerId(buyerId);

        return CheckoutResponseDTO.builder()

                .orderId(order.getId())

                .orderNumber(order.getOrderNumber())

                .totalAmount(order.getTotalAmount())

                .message("Order Placed Successfully")

                .build();

    }

}