package com.indusmart.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "subscription_plans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * BASIC
     * PROFESSIONAL
     * PREMIUM
     */
    @Column(nullable = false, unique = true)
    private String planName;

    /**
     * Price of subscription
     * Example:
     * 5999
     * 7999
     * 12999
     */
    @Column(nullable = false)
    private BigDecimal price;

    /**
     * Number of products seller can upload
     * Premium = -1 (Unlimited)
     */
    @Column(nullable = false)
    private Integer productLimit;

    /**
     * Validity in Days
     * Example: 365 Days
     */
    @Column(nullable = false)
    private Integer validityDays;

    /**
     * Seller gets homepage featured listing
     */
    private boolean featuredListing;

    /**
     * AI Recommendation Enabled
     */
    private boolean aiRecommendation;

    /**
     * AI Chatbot Enabled
     */
    private boolean aiChatbot;

    /**
     * Priority Customer Support
     */
    private boolean prioritySupport;

    /**
     * Active Plan
     */
    private boolean active;
}