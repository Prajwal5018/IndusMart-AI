package com.indusmart.service;

import com.indusmart.dto.AdminDashboardDTO;
import com.indusmart.entity.Order;
import com.indusmart.entity.SellerSubscription;
import com.indusmart.repository.CompanyRepository;
import com.indusmart.repository.OrderRepository;
import com.indusmart.repository.ProductRepository;
import com.indusmart.repository.ReviewRepository;
import com.indusmart.repository.SellerSubscriptionRepository;
import com.indusmart.repository.UserRepository;
import com.indusmart.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AdminDashboardService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ReviewRepository reviewRepository;
    private final WishlistRepository wishlistRepository;
    private final SellerSubscriptionRepository subscriptionRepository;

    public AdminDashboardService(
            UserRepository userRepository,
            CompanyRepository companyRepository,
            ProductRepository productRepository,
            OrderRepository orderRepository,
            ReviewRepository reviewRepository,
            WishlistRepository wishlistRepository,
            SellerSubscriptionRepository subscriptionRepository) {

        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.reviewRepository = reviewRepository;
        this.wishlistRepository = wishlistRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    /**
     * Admin Dashboard
     */
    public AdminDashboardDTO getDashboard() {

        long totalUsers =
                userRepository.count();

        long totalCompanies =
                companyRepository.count();

        long totalProducts =
                productRepository.count();

        List<Order> orders =
                orderRepository.findAll();

        long totalOrders =
                orders.size();

        BigDecimal totalRevenue =
                BigDecimal.ZERO;

        for (Order order : orders) {

            if ("SUCCESS".equalsIgnoreCase(
                    order.getPaymentStatus())) {

                totalRevenue =
                        totalRevenue.add(
                                order.getTotalAmount()
                        );
            }
        }

        long totalReviews =
                reviewRepository.count();

        long totalWishlistItems =
                wishlistRepository.count();

        List<SellerSubscription> subscriptions =
                subscriptionRepository.findAll();

        long activeSubscriptions = 0;

        for (SellerSubscription subscription : subscriptions) {

            if (subscription.isActive()) {

                activeSubscriptions++;

            }

        }
        return AdminDashboardDTO.builder()

                // Users
                .totalUsers(totalUsers)

                // Companies
                .totalCompanies(totalCompanies)

                // Products
                .totalProducts(totalProducts)

                // Orders
                .totalOrders(totalOrders)

                // Revenue
                .totalRevenue(totalRevenue)

                // Reviews
                .totalReviews(totalReviews)

                // Wishlist
                .totalWishlistItems(totalWishlistItems)

                // Subscriptions
                .activeSubscriptions(activeSubscriptions)

                .build();
    }

}