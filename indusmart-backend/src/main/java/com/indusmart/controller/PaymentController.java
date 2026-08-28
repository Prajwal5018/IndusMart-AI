package com.indusmart.controller;

import com.indusmart.dto.PaymentRequestDTO;
import com.indusmart.dto.PaymentResponseDTO;
import com.indusmart.service.RazorpayService;
import com.razorpay.RazorpayException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "*")
public class PaymentController {

    private final RazorpayService razorpayService;

    public PaymentController(RazorpayService razorpayService) {
        this.razorpayService = razorpayService;
    }

    /**
     * Create Razorpay Order
     */
    @PostMapping("/create-order")
    public ResponseEntity<PaymentResponseDTO> createOrder(
            @RequestBody PaymentRequestDTO request)
            throws RazorpayException {

        PaymentResponseDTO response =
                razorpayService.createOrder(request);

        return ResponseEntity.ok(response);
    }

    /**
     * Verify Razorpay Payment
     */
    @PostMapping("/verify/{orderId}")
    public ResponseEntity<String> verifyPayment(
            @PathVariable Long orderId,
            @RequestBody Map<String, String> payload)
            throws RazorpayException {

        boolean verified = razorpayService.verifyPayment(
                orderId,
                payload.get("razorpay_order_id"),
                payload.get("razorpay_payment_id"),
                payload.get("razorpay_signature")
        );

        if (verified) {
            return ResponseEntity.ok("Payment Verified Successfully");
        }

        return ResponseEntity.badRequest()
                .body("Payment Verification Failed");
    }

    /**
     * Get Payment Status
     */
    @GetMapping("/status/{orderId}")
    public ResponseEntity<String> getPaymentStatus(
            @PathVariable Long orderId) {

        return ResponseEntity.ok(
                razorpayService.getPaymentStatus(orderId)
        );
    }

}