package com.indusmart.service;

import com.indusmart.dto.ChatResponseDTO;
import com.indusmart.entity.Product;
import com.indusmart.repository.CategoryRepository;
import com.indusmart.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class ChatbotService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ChatbotService(ProductRepository productRepository,
                          CategoryRepository categoryRepository) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    /**
     * AI Chatbot
     */
    public ChatResponseDTO chat(String message) {

        if (message == null || message.isBlank()) {

            return ChatResponseDTO.builder()
                    .response("Please enter your question.")
                    .build();

        }

        String query = message.toLowerCase(Locale.ENGLISH);

        /*
         * Greetings
         */
        if (query.contains("hi")
                || query.contains("hello")
                || query.contains("hey")) {

            return ChatResponseDTO.builder()
                    .response(
                            "Hello 👋 Welcome to IndusMart AI. How can I help you today?"
                    )
                    .build();
        }

        /*
         * Product Search
         */
        if (query.contains("product")
                || query.contains("machine")
                || query.contains("equipment")) {

            List<Product> products =
                    productRepository.findAll();

            if (products.isEmpty()) {

                return ChatResponseDTO.builder()
                        .response("Currently no products are available.")
                        .build();

            }

            String names = products.stream()
                    .limit(10)
                    .map(Product::getProductName)
                    .collect(Collectors.joining(", "));

            return ChatResponseDTO.builder()
                    .response(
                            "Available products include: " + names
                    )
                    .build();
        }

        /*
         * Categories
         */
        if (query.contains("category")
                || query.contains("categories")) {

            String categories =
                    categoryRepository.findAll()
                            .stream()
                            .map(c -> c.getCategoryName())
                            .collect(Collectors.joining(", "));

            if (categories.isBlank()) {

                categories = "No categories available.";

            }

            return ChatResponseDTO.builder()
                    .response(
                            "Available categories are: " + categories
                    )
                    .build();
        }

        /*
         * Rent
         */
        if (query.contains("rent")
                || query.contains("rental")) {

            return ChatResponseDTO.builder()
                    .response(
                            "You can rent available industrial equipment directly from the product page."
                    )
                    .build();
        }

        /*
         * Buy
         */
        if (query.contains("buy")
                || query.contains("purchase")) {

            return ChatResponseDTO.builder()
                    .response(
                            "You can purchase products by adding them to your cart and completing payment."
                    )
                    .build();
        }

        /*
         * Payment
         */
        if (query.contains("payment")
                || query.contains("pay")) {

            return ChatResponseDTO.builder()
                    .response(
                            "IndusMart currently supports secure Razorpay payments."
                    )
                    .build();
        }

        /*
         * Seller
         */
        if (query.contains("seller")) {

            return ChatResponseDTO.builder()
                    .response(
                            "You can register as a seller, purchase a subscription plan, and upload industrial products."
                    )
                    .build();
        }

        /*
         * Delivery
         */
        if (query.contains("delivery")
                || query.contains("shipping")) {

            return ChatResponseDTO.builder()
                    .response(
                            "Delivery status can be viewed from your Orders section."
                    )
                    .build();
        }

        /*
         * Default
         */
        return ChatResponseDTO.builder()
                .response(
                        "Sorry, I couldn't understand your question. Please try asking about products, categories, rentals, payments, sellers or delivery."
                )
                .build();

    }

}