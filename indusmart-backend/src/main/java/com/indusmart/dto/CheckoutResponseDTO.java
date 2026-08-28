package com.indusmart.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckoutResponseDTO {

    /**
     * Database Order ID
     */
    private Long orderId;

    /**
     * Order Number
     */
    private String orderNumber;

    /**
     * Total Amount
     */
    private BigDecimal totalAmount;

    /**
     * Response Message
     */
    private String message;

}