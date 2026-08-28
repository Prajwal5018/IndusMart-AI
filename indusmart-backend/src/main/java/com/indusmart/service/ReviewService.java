package com.indusmart.service;

import com.indusmart.entity.Product;
import com.indusmart.entity.Review;
import com.indusmart.entity.User;
import com.indusmart.repository.ProductRepository;
import com.indusmart.repository.ReviewRepository;
import com.indusmart.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ReviewService(ReviewRepository reviewRepository,
                         UserRepository userRepository,
                         ProductRepository productRepository) {

        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    /**
     * Add Review
     */
    public String addReview(Long buyerId,
                            Long productId,
                            Integer rating,
                            String reviewText) {

        if (rating < 1 || rating > 5) {
            return "Rating must be between 1 and 5";
        }

        Optional<User> buyer =
                userRepository.findById(buyerId);

        if (buyer.isEmpty()) {
            return "Buyer not found";
        }

        Optional<Product> product =
                productRepository.findById(productId);

        if (product.isEmpty()) {
            return "Product not found";
        }

        if (reviewRepository.existsByBuyerIdAndProductId(
                buyerId,
                productId)) {

            return "You have already reviewed this product";
        }

        Review review = Review.builder()
                .buyer(buyer.get())
                .product(product.get())
                .rating(rating)
                .review(reviewText)
                .reviewedAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        reviewRepository.save(review);

        return "Review added successfully";
    }

    /**
     * Update Review
     */
    public String updateReview(Long buyerId,
                               Long productId,
                               Integer rating,
                               String reviewText) {

        Optional<Review> optionalReview =
                reviewRepository.findByBuyerIdAndProductId(
                        buyerId,
                        productId
                );

        if (optionalReview.isEmpty()) {
            return "Review not found";
        }

        Review review = optionalReview.get();

        review.setRating(rating);
        review.setReview(reviewText);
        review.setUpdatedAt(LocalDateTime.now());

        reviewRepository.save(review);

        return "Review updated successfully";
    }

    /**
     * Delete Review
     */
    public String deleteReview(Long buyerId,
                               Long productId) {

        if (!reviewRepository.existsByBuyerIdAndProductId(
                buyerId,
                productId)) {

            return "Review not found";
        }

        reviewRepository.deleteByBuyerIdAndProductId(
                buyerId,
                productId
        );

        return "Review deleted successfully";
    }

    /**
     * Product Reviews
     */
    public List<Review> getProductReviews(Long productId) {

        return reviewRepository
                .findByProductIdOrderByReviewedAtDesc(productId);
    }

    /**
     * Buyer Reviews
     */
    public List<Review> getBuyerReviews(Long buyerId) {

        return reviewRepository
                .findByBuyerIdOrderByReviewedAtDesc(buyerId);
    }

    /**
     * Average Rating
     */
    public Double getAverageRating(Long productId) {

        Double rating =
                reviewRepository.getAverageRating(productId);

        return rating == null ? 0.0 : rating;
    }

    /**
     * Review Count
     */
    public long reviewCount(Long productId) {

        return reviewRepository.countByProductId(productId);
    }

    /**
     * Rating Count
     */
    public long ratingCount(Long productId,
                            Integer rating) {

        return reviewRepository
                .countByProductIdAndRating(
                        productId,
                        rating
                );
    }

}