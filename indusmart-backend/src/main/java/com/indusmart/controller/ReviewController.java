package com.indusmart.controller;

import com.indusmart.dto.ReviewRequest;
import com.indusmart.entity.Review;
import com.indusmart.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * Add Review
     */
    @PostMapping("/add")
    public ResponseEntity<String> addReview(
            @Valid @RequestBody ReviewRequest request) {

        return ResponseEntity.ok(
                reviewService.addReview(
                        request.getBuyerId(),
                        request.getProductId(),
                        request.getRating(),
                        request.getReview()
                )
        );
    }

    /**
     * Update Review
     */
    @PutMapping("/update")
    public ResponseEntity<String> updateReview(
            @Valid @RequestBody ReviewRequest request) {

        return ResponseEntity.ok(
                reviewService.updateReview(
                        request.getBuyerId(),
                        request.getProductId(),
                        request.getRating(),
                        request.getReview()
                )
        );
    }

    /**
     * Delete Review
     */
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteReview(
            @RequestParam Long buyerId,
            @RequestParam Long productId) {

        return ResponseEntity.ok(
                reviewService.deleteReview(
                        buyerId,
                        productId
                )
        );
    }

    /**
     * Get Reviews Of Product
     */
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getProductReviews(
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                reviewService.getProductReviews(productId)
        );
    }

    /**
     * Get Reviews Of Buyer
     */
    @GetMapping("/buyer/{buyerId}")
    public ResponseEntity<List<Review>> getBuyerReviews(
            @PathVariable Long buyerId) {

        return ResponseEntity.ok(
                reviewService.getBuyerReviews(buyerId)
        );
    }

    /**
     * Average Rating
     */
    @GetMapping("/average/{productId}")
    public ResponseEntity<Double> averageRating(
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                reviewService.getAverageRating(productId)
        );
    }

    /**
     * Total Reviews
     */
    @GetMapping("/count/{productId}")
    public ResponseEntity<Long> reviewCount(
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                reviewService.reviewCount(productId)
        );
    }

    /**
     * Rating Distribution
     */
    @GetMapping("/rating-count/{productId}/{rating}")
    public ResponseEntity<Long> ratingCount(
            @PathVariable Long productId,
            @PathVariable Integer rating) {

        return ResponseEntity.ok(
                reviewService.ratingCount(
                        productId,
                        rating
                )
        );
    }

}