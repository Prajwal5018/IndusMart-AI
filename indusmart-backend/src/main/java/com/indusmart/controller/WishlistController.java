package com.indusmart.controller;

import com.indusmart.entity.Wishlist;
import com.indusmart.service.WishlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@CrossOrigin(origins = "*")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    /**
     * Add Product to Wishlist
     */
    @PostMapping("/add")
    public ResponseEntity<String> addToWishlist(
            @RequestParam Long buyerId,
            @RequestParam Long productId) {

        return ResponseEntity.ok(
                wishlistService.addToWishlist(
                        buyerId,
                        productId
                )
        );
    }

    /**
     * Remove Product from Wishlist
     */
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFromWishlist(
            @RequestParam Long buyerId,
            @RequestParam Long productId) {

        return ResponseEntity.ok(
                wishlistService.removeFromWishlist(
                        buyerId,
                        productId
                )
        );
    }

    /**
     * Get Buyer's Wishlist
     */
    @GetMapping("/{buyerId}")
    public ResponseEntity<List<Wishlist>> getWishlist(
            @PathVariable Long buyerId) {

        return ResponseEntity.ok(
                wishlistService.getWishlist(buyerId)
        );
    }

    /**
     * Check if Product Exists in Wishlist
     */
    @GetMapping("/check")
    public ResponseEntity<Boolean> isWishlisted(
            @RequestParam Long buyerId,
            @RequestParam Long productId) {

        return ResponseEntity.ok(
                wishlistService.isWishlisted(
                        buyerId,
                        productId
                )
        );
    }

    /**
     * Wishlist Count
     */
    @GetMapping("/count/{buyerId}")
    public ResponseEntity<Long> wishlistCount(
            @PathVariable Long buyerId) {

        return ResponseEntity.ok(
                wishlistService.wishlistCount(buyerId)
        );
    }

    /**
     * Clear Wishlist
     */
    @DeleteMapping("/clear/{buyerId}")
    public ResponseEntity<String> clearWishlist(
            @PathVariable Long buyerId) {

        return ResponseEntity.ok(
                wishlistService.clearWishlist(buyerId)
        );
    }

    /**
     * Product Wishlist Count
     */
    @GetMapping("/product-count/{productId}")
    public ResponseEntity<Long> productWishlistCount(
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                wishlistService.productWishlistCount(productId)
        );
    }

}