package com.indusmart.service;

import com.indusmart.dto.SellerDashboardDTO;
import com.indusmart.entity.Company;
import com.indusmart.entity.OrderItem;
import com.indusmart.entity.Product;
import com.indusmart.exception.ResourceNotFoundException;
import com.indusmart.repository.CompanyRepository;
import com.indusmart.repository.OrderItemRepository;
import com.indusmart.repository.ProductRepository;
import com.indusmart.repository.ReviewRepository;
import com.indusmart.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SellerDashboardService {

    private final CompanyRepository companyRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final ReviewRepository reviewRepository;
    private final WishlistRepository wishlistRepository;

    public SellerDashboardService(
            CompanyRepository companyRepository,
            ProductRepository productRepository,
            OrderItemRepository orderItemRepository,
            ReviewRepository reviewRepository,
            WishlistRepository wishlistRepository) {

        this.companyRepository = companyRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
        this.reviewRepository = reviewRepository;
        this.wishlistRepository = wishlistRepository;
    }

    /**
     * Seller Dashboard
     */
    public SellerDashboardDTO getDashboard(Long companyId) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Company not found"));

        List<Product> products =
                productRepository.findByCompanyId(companyId);

        long totalProducts = products.size();
        long availableProducts = 0;
        long outOfStockProducts = 0;
        long lowStockProducts = 0;

        long totalReviews = 0;
        long wishlistCount = 0;

        double totalRating = 0;

        for (Product product : products) {

            if (product.getQuantity() != null && product.getQuantity() > 0) {
                availableProducts++;
            } else {
                outOfStockProducts++;
            }

            if (product.getQuantity() != null &&
                    product.getQuantity() <= 5) {
                lowStockProducts++;
            }

            long reviews =
                    reviewRepository.countByProductId(product.getId());

            totalReviews += reviews;

            Double average =
                    reviewRepository.getAverageRating(product.getId());

            if (average != null) {
                totalRating += average;
            }

            wishlistCount +=
                    wishlistRepository.countByProductId(product.getId());
        }

        double averageRating = 0;

        if (!products.isEmpty()) {
            averageRating = totalRating / products.size();
        }

        List<OrderItem> sellerOrders =
                orderItemRepository.findByCompanyId(companyId);

        long totalOrders = sellerOrders.size();
        long pendingOrders = 0;
        long confirmedOrders = 0;
        long shippedOrders = 0;
        long deliveredOrders = 0;
        long cancelledOrders = 0;

        BigDecimal revenue = BigDecimal.ZERO;

        for (OrderItem item : sellerOrders) {

            String status = item.getStatus();

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

                    if (item.getTotalPrice() != null) {
                        revenue = revenue.add(item.getTotalPrice());
                    }
                    break;

                case "CANCELLED":
                    cancelledOrders++;
                    break;

                default:
                    break;
            }
        }

        return SellerDashboardDTO.builder()

                // Company
                .companyId(company.getId())
                .companyName(company.getCompanyName())

                // Product Statistics
                .totalProducts(totalProducts)
                .availableProducts(availableProducts)
                .outOfStockProducts(outOfStockProducts)

                // Orders
                .totalOrders(totalOrders)
                .pendingOrders(pendingOrders)
                .confirmedOrders(confirmedOrders)
                .shippedOrders(shippedOrders)
                .deliveredOrders(deliveredOrders)
                .cancelledOrders(cancelledOrders)

                // Revenue
                .totalRevenue(revenue)

                // Reviews
                .averageRating(averageRating)
                .totalReviews(totalReviews)

                // Wishlist
                .wishlistCount(wishlistCount)

                // Inventory
                .lowStockProducts(lowStockProducts)

                .build();
    }

}