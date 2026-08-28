package com.indusmart.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AIChatResponse {

    /**
     * AI Response
     */
    private String response;

    /**
     * Recommended Products
     */
    private List<ProductRecommendationDTO> recommendations;

    /**
     * AI Confidence Score
     */
    private Double confidence;

    /**
     * Response Time
     */
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

}