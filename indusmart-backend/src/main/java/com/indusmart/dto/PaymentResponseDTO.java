package com.indusmart.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponseDTO {

    /**
     * Order ID in Database
     */
    private Long orderId;

    /**
     * Razorpay Order ID
     */
    private String razorpayOrderId;

    /**
     * Amount (in Paise)
     */
    private Integer amount;

    /**
     * Currency
     */
    private String currency;

    /**
     * Razorpay Key
     */
    private String key;

    /**
     * Status
     */
    private String status;

    /**
     * Message
     */
    private String message;

}