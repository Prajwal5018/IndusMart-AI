package com.indusmart.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequestDTO {

    /**
     * Order ID
     */
    private Long orderId;

    /**
     * Amount
     */
    private BigDecimal amount;

    /**
     * Currency
     */
    @Builder.Default
    private String currency = "INR";

    /**
     * Buyer Name
     */
    private String customerName;

    /**
     * Buyer Email
     */
    private String customerEmail;

    /**
     * Buyer Phone
     */
    private String customerPhone;

}