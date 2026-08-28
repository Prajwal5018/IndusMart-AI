package com.indusmart.controller;

import com.indusmart.entity.Cart;
import com.indusmart.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    /**
     * Add Product To Cart
     */
    @PostMapping("/add")
    public ResponseEntity<String> addToCart(
            @RequestParam Long buyerId,
            @RequestParam Long productId,
            @RequestParam Integer quantity,
            @RequestParam(defaultValue = "false") boolean rental,
            @RequestParam(defaultValue = "0") Integer rentalDays) {

        return ResponseEntity.ok(
                cartService.addToCart(
                        buyerId,
                        productId,
                        quantity,
                        rental,
                        rentalDays
                )
        );
    }

    /**
     * Get Buyer's Cart
     */
    @GetMapping("/{buyerId}")
    public ResponseEntity<List<Cart>> getBuyerCart(
            @PathVariable Long buyerId) {

        return ResponseEntity.ok(
                cartService.getBuyerCart(buyerId)
        );
    }

    /**
     * Update Quantity
     */
    @PutMapping("/update")
    public ResponseEntity<String> updateQuantity(
            @RequestParam Long cartId,
            @RequestParam Integer quantity) {

        return ResponseEntity.ok(
                cartService.updateQuantity(cartId, quantity)
        );
    }

    /**
     * Remove Cart Item
     */
    @DeleteMapping("/remove/{cartId}")
    public ResponseEntity<String> removeItem(
            @PathVariable Long cartId) {

        return ResponseEntity.ok(
                cartService.removeItem(cartId)
        );
    }

    /**
     * Clear Buyer's Cart
     */
    @DeleteMapping("/clear/{buyerId}")
    public ResponseEntity<String> clearCart(
            @PathVariable Long buyerId) {

        return ResponseEntity.ok(
                cartService.clearCart(buyerId)
        );
    }

    /**
     * Calculate Cart Total
     */
    @GetMapping("/total/{buyerId}")
    public ResponseEntity<BigDecimal> calculateTotal(
            @PathVariable Long buyerId) {

        return ResponseEntity.ok(
                cartService.calculateCartTotal(buyerId)
        );
    }

}