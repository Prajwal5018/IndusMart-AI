package com.indusmart.service;

import com.indusmart.dto.AIChatResponse;
import com.indusmart.dto.ProductRecommendationDTO;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Service
public class AIChatService {

    private final RecommendationService recommendationService;

    public AIChatService(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    /**
     * AI Chat
     */
    public AIChatResponse chat(String message) {

        String userMessage = message == null
                ? ""
                : message.toLowerCase(Locale.ENGLISH);

        String response;

        if (userMessage.contains("generator")) {

            response =
                    "To see generator recommendations, open any generator product and view its similar recommended products.";

        } else if (userMessage.contains("crane")) {

            response =
                    "To see crane recommendations, open any crane product and view its recommendations.";

        } else if (userMessage.contains("excavator")) {

            response =
                    "To see excavator recommendations, open any excavator product and view its recommendations.";

        } else if (userMessage.contains("rent")) {

            response =
                    "IndusMart AI supports equipment rentals. Open a product to view rental options.";

        } else if (userMessage.contains("buy")) {

            response =
                    "IndusMart AI allows you to purchase industrial equipment securely.";

        } else if (userMessage.contains("hello")
                || userMessage.contains("hi")) {

            response =
                    "Hello! Welcome to IndusMart AI. How can I help you today?";

        } else {

            response =
                    "I can help you with product search, rentals, purchases, orders, payments and recommendations.";
        }

        return AIChatResponse.builder()
                .response(response)
                .recommendations(Collections.<ProductRecommendationDTO>emptyList())
                .confidence(95.0)
                .build();
    }
}