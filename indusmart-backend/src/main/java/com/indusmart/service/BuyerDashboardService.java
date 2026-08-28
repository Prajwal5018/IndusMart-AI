package com.indusmart.service;

import com.indusmart.dto.BuyerDashboardDTO;
import com.indusmart.entity.Order;
import com.indusmart.entity.User;
import com.indusmart.exception.ResourceNotFoundException;
import com.indusmart.repository.CartRepository;
import com.indusmart.repository.OrderRepository;
import com.indusmart.repository.ReviewRepository;
import com.indusmart.repository.UserRepository;
import com.indusmart.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BuyerDashboardService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final WishlistRepository wishlistRepository;
    private final ReviewRepository reviewRepository;

    public BuyerDashboardService(
            UserRepository userRepository,
            CartRepository cartRepository,
            OrderRepository orderRepository,
            WishlistRepository wishlistRepository,
            ReviewRepository reviewRepository) {

        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.wishlistRepository = wishlistRepository;
        this.reviewRepository = reviewRepository;
    }

    /**
     * Buyer Dashboard
     */
    public BuyerDashboardDTO getDashboard(Long buyerId) {

        User buyer = userRepository.findById(buyerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Buyer not found"));

        long cartItems =
                cartRepository.countByBuyerId(buyerId);

        long wishlistItems =
                wishlistRepository.countByBuyerId(buyerId);

        List<Order> orders =
                orderRepository.findByBuyerIdOrderByOrderDateDesc(buyerId);

        long totalOrders = orders.size();

        long pendingOrders = 0;
        long confirmedOrders = 0;
        long shippedOrders = 0;
        long deliveredOrders = 0;
        long cancelledOrders = 0;

        BigDecimal totalSpent = BigDecimal.ZERO;

        for (Order order : orders) {

            String status = order.getOrderStatus();

            if (status == null) {
                continue;
            }

            switch (status.toUpperCase()) {

                case "PENDING":
                    pendingOrders++;
                    break;

                case "CONFIRMED":
                    confirmedOrders++;
                    break;

                case "SHIPPED":
                    shippedOrders++;
                    break;

                case "DELIVERED":
                    deliveredOrders++;

                    if (order.getTotalAmount() != null) {
                        totalSpent = totalSpent.add(order.getTotalAmount());
                    }
                    break;

                case "CANCELLED":
                    cancelledOrders++;
                    break;

                default:
                    break;
            }
        }

        long reviewsWritten =
                reviewRepository
                        .findByBuyerIdOrderByReviewedAtDesc(buyerId)
                        .size();

        return BuyerDashboardDTO.builder()

                // Buyer
                .buyerId(buyer.getId())
                .buyerName(buyer.getFullName())
                .email(buyer.getEmail())

                // Cart
                .cartItems(cartItems)

                // Orders
                .totalOrders(totalOrders)
                .pendingOrders(pendingOrders)
                .confirmedOrders(confirmedOrders)
                .shippedOrders(shippedOrders)
                .deliveredOrders(deliveredOrders)
                .cancelledOrders(cancelledOrders)

                // Wishlist
                .wishlistItems(wishlistItems)

                // Reviews
                .reviewsWritten(reviewsWritten)

                // Spending
                .totalSpent(totalSpent)

                .build();
    }

}