package com.indusmart.service;

import com.indusmart.dto.PaymentRequestDTO;
import com.indusmart.dto.PaymentResponseDTO;
import com.indusmart.entity.Order;
import com.indusmart.exception.ResourceNotFoundException;
import com.indusmart.repository.OrderRepository;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RazorpayService {

    @Value("${razorpay.key.id}")
    private String keyId;

    @Value("${razorpay.key.secret}")
    private String keySecret;

    private final RazorpayClient razorpayClient;
    private final OrderRepository orderRepository;

    public RazorpayService(RazorpayClient razorpayClient,
                           OrderRepository orderRepository) {

        this.razorpayClient = razorpayClient;
        this.orderRepository = orderRepository;
    }

    /**
     * Create Razorpay Order
     */
    public PaymentResponseDTO createOrder(PaymentRequestDTO request)
            throws RazorpayException {

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order not found"));

        JSONObject options = new JSONObject();

        options.put(
                "amount",
                request.getAmount()
                        .multiply(BigDecimal.valueOf(100))
                        .intValue()
        );

        options.put(
                "currency",
                request.getCurrency()
        );

        options.put(
                "receipt",
                "INDUSMART_" + order.getId()
        );

        com.razorpay.Order razorpayOrder =
                razorpayClient.orders.create(options);

        order.setRazorpayOrderId(
                razorpayOrder.get("id")
        );

        orderRepository.save(order);

        return PaymentResponseDTO.builder()
                .orderId(order.getId())
                .razorpayOrderId(
                        razorpayOrder.get("id").toString()
                )
                .amount(
                        razorpayOrder.get("amount")
                )
                .currency(
                        razorpayOrder.get("currency").toString()
                )
                .key(keyId)
                .status("SUCCESS")
                .message("Payment Order Created Successfully")
                .build();
    }

    /**
     * Verify Payment Signature
     */
    public boolean verifyPayment(Long orderId,
                                 String razorpayOrderId,
                                 String razorpayPaymentId,
                                 String razorpaySignature)
            throws RazorpayException {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order not found"));

        JSONObject attributes = new JSONObject();

        attributes.put(
                "razorpay_order_id",
                razorpayOrderId
        );

        attributes.put(
                "razorpay_payment_id",
                razorpayPaymentId
        );

        attributes.put(
                "razorpay_signature",
                razorpaySignature
        );

        boolean verified =
                com.razorpay.Utils.verifyPaymentSignature(
                        attributes,
                        keySecret
                );

        if (verified) {

            order.setRazorpayPaymentId(
                    razorpayPaymentId
            );

            order.setRazorpaySignature(
                    razorpaySignature
            );

            order.setPaymentStatus("SUCCESS");

            order.setOrderStatus("CONFIRMED");

            orderRepository.save(order);

        } else {

            order.setPaymentStatus("FAILED");

            orderRepository.save(order);
        }

        return verified;
    }

    /**
     * Get Payment Status
     */
    public String getPaymentStatus(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order not found"));

        return order.getPaymentStatus();
    }

}