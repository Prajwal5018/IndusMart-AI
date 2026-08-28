package com.indusmart.service;

import com.indusmart.entity.Cart;
import com.indusmart.entity.Product;
import com.indusmart.entity.User;
import com.indusmart.repository.CartRepository;
import com.indusmart.repository.ProductRepository;
import com.indusmart.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository,
                       ProductRepository productRepository,
                       UserRepository userRepository) {

        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    /**
     * Add Product to Cart
     */
    public String addToCart(Long buyerId,
                            Long productId,
                            Integer quantity,
                            boolean rental,
                            Integer rentalDays) {

        Optional<User> buyerOptional = userRepository.findById(buyerId);

        if (buyerOptional.isEmpty()) {
            return "Buyer not found";
        }

        Optional<Product> productOptional =
                productRepository.findById(productId);

        if (productOptional.isEmpty()) {
            return "Product not found";
        }

        Product product = productOptional.get();

        if (quantity <= 0) {
            return "Quantity must be greater than zero";
        }

        if (quantity > product.getQuantity()) {
            return "Insufficient stock available";
        }

        Optional<Cart> existingCart =
                cartRepository.findByBuyerIdAndProductId(
                        buyerId,
                        productId
                );

        BigDecimal unitPrice = rental
                ? product.getRentalPricePerDay()
                : product.getPurchasePrice();

        if (rental && rentalDays <= 0) {
            return "Rental days must be greater than zero";
        }

        BigDecimal totalPrice;

        if (rental) {

            totalPrice = unitPrice
                    .multiply(BigDecimal.valueOf(quantity))
                    .multiply(BigDecimal.valueOf(rentalDays));

        } else {

            totalPrice = unitPrice
                    .multiply(BigDecimal.valueOf(quantity));

        }

        if (existingCart.isPresent()) {

            Cart cart = existingCart.get();

            int newQuantity = cart.getQuantity() + quantity;

            if (newQuantity > product.getQuantity()) {
                return "Insufficient stock available";
            }

            cart.setQuantity(newQuantity);
            cart.setRental(rental);
            cart.setRentalDays(rentalDays);
            cart.setUnitPrice(unitPrice);

            if (rental) {

                cart.setTotalPrice(
                        unitPrice.multiply(
                                        BigDecimal.valueOf(newQuantity))
                                .multiply(
                                        BigDecimal.valueOf(rentalDays))
                );

            } else {

                cart.setTotalPrice(
                        unitPrice.multiply(
                                BigDecimal.valueOf(newQuantity))
                );

            }

            cartRepository.save(cart);

            return "Cart Updated Successfully";
        }

        Cart cart = Cart.builder()
                .buyer(buyerOptional.get())
                .product(product)
                .quantity(quantity)
                .unitPrice(unitPrice)
                .totalPrice(totalPrice)
                .rental(rental)
                .rentalDays(rentalDays)
                .build();

        cartRepository.save(cart);

        return "Product Added To Cart";
    }

    /**
     * Get Buyer's Cart
     */
    public List<Cart> getBuyerCart(Long buyerId) {

        return cartRepository.findByBuyerId(buyerId);

    }

    /**
     * Remove Item From Cart
     */
    public String removeItem(Long cartId) {

        Optional<Cart> optionalCart =
                cartRepository.findById(cartId);

        if (optionalCart.isEmpty()) {
            return "Cart Item Not Found";
        }

        cartRepository.delete(optionalCart.get());

        return "Item Removed Successfully";
    }

    /**
     * Clear Buyer's Cart
     */
    public String clearCart(Long buyerId) {

        cartRepository.deleteByBuyerId(buyerId);

        return "Cart Cleared Successfully";
    }

    /**
     * Update Quantity
     */
    public String updateQuantity(Long cartId,
                                 Integer quantity) {

        Optional<Cart> optionalCart =
                cartRepository.findById(cartId);

        if (optionalCart.isEmpty()) {
            return "Cart Item Not Found";
        }

        Cart cart = optionalCart.get();

        if (quantity <= 0) {
            return "Quantity must be greater than zero";
        }

        if (quantity > cart.getProduct().getQuantity()) {
            return "Insufficient Stock";
        }

        cart.setQuantity(quantity);

        if (cart.isRental()) {

            cart.setTotalPrice(
                    cart.getUnitPrice()
                            .multiply(BigDecimal.valueOf(quantity))
                            .multiply(BigDecimal.valueOf(cart.getRentalDays()))
            );

        } else {

            cart.setTotalPrice(
                    cart.getUnitPrice()
                            .multiply(BigDecimal.valueOf(quantity))
            );

        }

        cartRepository.save(cart);

        return "Quantity Updated Successfully";
    }

    /**
     * Calculate Cart Total
     */
    public BigDecimal calculateCartTotal(Long buyerId) {

        List<Cart> cartItems =
                cartRepository.findByBuyerId(buyerId);

        BigDecimal total = BigDecimal.ZERO;

        for (Cart item : cartItems) {

            total = total.add(item.getTotalPrice());

        }

        return total;
    }
}