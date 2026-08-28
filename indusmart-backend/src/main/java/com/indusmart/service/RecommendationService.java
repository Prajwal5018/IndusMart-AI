package com.indusmart.service;

import com.indusmart.dto.ProductRecommendationDTO;
import com.indusmart.entity.Product;
import com.indusmart.exception.ResourceNotFoundException;
import com.indusmart.repository.ProductRepository;
import com.indusmart.repository.ReviewRepository;
import com.indusmart.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class RecommendationService {

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final WishlistRepository wishlistRepository;

    public RecommendationService(
            ProductRepository productRepository,
            ReviewRepository reviewRepository,
            WishlistRepository wishlistRepository) {

        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
        this.wishlistRepository = wishlistRepository;
    }

    /**
     * Recommend Products
     */
    public List<ProductRecommendationDTO> recommendProducts(Long productId) {

        Product currentProduct = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found"));

        Set<Product> recommendations = new LinkedHashSet<>();

        // Same Category
        recommendations.addAll(
                productRepository.findByCategoryAndIdNot(
                        currentProduct.getCategory(),
                        productId
                )
        );

        // Same Brand
        recommendations.addAll(
                productRepository.findByBrandAndIdNot(
                        currentProduct.getBrand(),
                        productId
                )
        );

        // Similar Price (±20%)
        if (currentProduct.getPurchasePrice() != null) {

            BigDecimal minPrice = currentProduct.getPurchasePrice()
                    .multiply(BigDecimal.valueOf(0.80));

            BigDecimal maxPrice = currentProduct.getPurchasePrice()
                    .multiply(BigDecimal.valueOf(1.20));

            recommendations.addAll(
                    productRepository.findByPurchasePriceBetween(
                            minPrice,
                            maxPrice
                    )
            );
        }

        List<ProductRecommendationDTO> result = new ArrayList<>();

        for (Product product : recommendations) {

            if (product.getId().equals(productId)) {
                continue;
            }

            if (product.getQuantity() == null ||
                    product.getQuantity() <= 0) {
                continue;
            }

            Double rating =
                    reviewRepository.getAverageRating(product.getId());

            if (rating == null) {
                rating = 0.0;
            }

            long wishlistCount =
                    wishlistRepository.countByProductId(product.getId());

            double score = calculateScore(
                    currentProduct,
                    product,
                    rating,
                    wishlistCount
            );

            result.add(
                    ProductRecommendationDTO.builder()
                            .productId(product.getId())
                            .productName(product.getProductName())
                            .category(product.getCategory())
                            .brand(product.getBrand())
                            .purchasePrice(product.getPurchasePrice())
                            .imageUrl(product.getImageUrl())
                            .averageRating(rating)
                            .wishlistCount(wishlistCount)
                            .recommendationScore(score)
                            .build()
            );
        }

        result.sort(
                Comparator.comparing(
                        ProductRecommendationDTO::getRecommendationScore
                ).reversed()
        );

        return result;
    }

    /**
     * Recommendation Score
     */
    private double calculateScore(
            Product current,
            Product candidate,
            Double rating,
            Long wishlistCount) {

        double score = 0;

        if (Objects.equals(
                current.getCategory(),
                candidate.getCategory())) {

            score += 40;
        }

        if (Objects.equals(
                current.getBrand(),
                candidate.getBrand())) {

            score += 25;
        }

        if (current.getPurchasePrice() != null &&
                candidate.getPurchasePrice() != null) {

            BigDecimal difference =
                    current.getPurchasePrice()
                            .subtract(candidate.getPurchasePrice())
                            .abs();

            if (difference.compareTo(
                    current.getPurchasePrice()
                            .multiply(BigDecimal.valueOf(0.20))) <= 0) {

                score += 20;
            }
        }

        score += rating * 2;

        score += Math.min(wishlistCount, 10);

        return BigDecimal.valueOf(score)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

}