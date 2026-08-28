package com.indusmart.service;

import com.indusmart.entity.Product;
import com.indusmart.entity.User;
import com.indusmart.entity.Wishlist;
import com.indusmart.repository.ProductRepository;
import com.indusmart.repository.UserRepository;
import com.indusmart.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public WishlistService(WishlistRepository wishlistRepository,
                           UserRepository userRepository,
                           ProductRepository productRepository) {

        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    /**
     * Add Product to Wishlist
     */
    public String addToWishlist(Long buyerId, Long productId) {

        Optional<User> buyerOptional =
                userRepository.findById(buyerId);

        if (buyerOptional.isEmpty()) {
            return "Buyer not found";
        }

        Optional<Product> productOptional =
                productRepository.findById(productId);

        if (productOptional.isEmpty()) {
            return "Product not found";
        }

        if (wishlistRepository.existsByBuyerIdAndProductId(
                buyerId,
                productId)) {

            return "Product already exists in wishlist";
        }

        Wishlist wishlist = Wishlist.builder()
                .buyer(buyerOptional.get())
                .product(productOptional.get())
                .build();

        wishlistRepository.save(wishlist);

        return "Product added to wishlist successfully";
    }

    /**
     * Remove Product from Wishlist
     */
    public String removeFromWishlist(Long buyerId,
                                     Long productId) {

        if (!wishlistRepository.existsByBuyerIdAndProductId(
                buyerId,
                productId)) {

            return "Product not found in wishlist";
        }

        wishlistRepository.deleteByBuyerIdAndProductId(
                buyerId,
                productId
        );

        return "Product removed from wishlist successfully";
    }

    /**
     * Get Buyer's Wishlist
     */
    public List<Wishlist> getWishlist(Long buyerId) {

        return wishlistRepository
                .findByBuyerIdOrderByAddedAtDesc(buyerId);
    }

    /**
     * Check Wishlist
     */
    public boolean isWishlisted(Long buyerId,
                                Long productId) {

        return wishlistRepository
                .existsByBuyerIdAndProductId(
                        buyerId,
                        productId
                );
    }

    /**
     * Wishlist Count
     */
    public long wishlistCount(Long buyerId) {

        return wishlistRepository.countByBuyerId(buyerId);
    }

    /**
     * Clear Wishlist
     */
    public String clearWishlist(Long buyerId) {

        wishlistRepository.deleteByBuyerId(buyerId);

        return "Wishlist cleared successfully";
    }

    /**
     * Product Wishlist Count
     */
    public long productWishlistCount(Long productId) {

        return wishlistRepository.countByProductId(productId);
    }

}