package com.indusmart.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckoutRequest {

    /**
     * Buyer ID
     */
    private Long buyerId;

    /**
     * Shipping Address
     */
    private String shippingAddress;

}