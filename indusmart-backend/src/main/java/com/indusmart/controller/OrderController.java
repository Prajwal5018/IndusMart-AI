package com.indusmart.controller;

import com.indusmart.dto.CheckoutRequest;
import com.indusmart.entity.Order;
import com.indusmart.service.OrderCheckoutService;
import com.indusmart.service.OrderManagementService;
import com.indusmart.service.OrderQueryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.indusmart.dto.CheckoutResponseDTO;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderCheckoutService orderCheckoutService;
    private final OrderManagementService orderManagementService;
    private final OrderQueryService orderQueryService;

    public OrderController(
            OrderCheckoutService orderCheckoutService,
            OrderManagementService orderManagementService,
            OrderQueryService orderQueryService) {

        this.orderCheckoutService = orderCheckoutService;
        this.orderManagementService = orderManagementService;
        this.orderQueryService = orderQueryService;
    }

    /**
     * Checkout Cart
     */
    @PostMapping("/checkout")
    public ResponseEntity<CheckoutResponseDTO> checkout(
            @Valid @RequestBody CheckoutRequest request) {

        return ResponseEntity.ok(

                orderCheckoutService.checkout(

                        request.getBuyerId(),

                        request.getShippingAddress()

                )

        );

    }

    /**
     * Buyer Order History
     */
    @GetMapping("/buyer/{buyerId}")
    public ResponseEntity<List<Order>> getBuyerOrders(
            @PathVariable Long buyerId) {

        return ResponseEntity.ok(
                orderQueryService.getOrdersByBuyer(buyerId)
        );
    }

    /**
     * Get Order By ID
     */
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(
            @PathVariable Long orderId) {

        Order order = orderQueryService.getOrder(orderId);

        if (order == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(order);
    }

    /**
     * Cancel Order
     */
    @PutMapping("/cancel/{orderId}")
    public ResponseEntity<String> cancelOrder(
            @PathVariable Long orderId) {

        return ResponseEntity.ok(
                orderManagementService.cancelOrder(orderId)
        );
    }

    /**
     * Update Order Status
     */
    @PutMapping("/status/{orderId}")
    public ResponseEntity<String> updateStatus(
            @PathVariable Long orderId,
            @RequestParam String status) {

        return ResponseEntity.ok(
                orderManagementService.updateOrderStatus(
                        orderId,
                        status
                )
        );
    }

    /**
     * Update Payment Status
     */
    @PutMapping("/payment/{orderId}")
    public ResponseEntity<String> updatePaymentStatus(
            @PathVariable Long orderId,
            @RequestParam String status) {

        return ResponseEntity.ok(
                orderManagementService.updatePaymentStatus(
                        orderId,
                        status
                )
        );
    }

}