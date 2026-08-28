package com.indusmart.controller;

import com.indusmart.entity.SubscriptionPlan;
import com.indusmart.service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscription")
@CrossOrigin(origins = "*")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    /**
     * Get all subscription plans
     */
    @GetMapping("/plans")
    public ResponseEntity<List<SubscriptionPlan>> getAllPlans() {

        return ResponseEntity.ok(subscriptionService.getAllPlans());

    }

    /**
     * Buy Subscription
     *
     * Example:
     * /api/subscription/buy?sellerId=1&planId=2
     */
    @PostMapping("/buy")
    public ResponseEntity<String> buySubscription(
            @RequestParam Long sellerId,
            @RequestParam Long planId) {

        String response =
                subscriptionService.buySubscription(sellerId, planId);

        return ResponseEntity.ok(response);

    }

    /**
     * Upgrade Subscription
     *
     * Example:
     * /api/subscription/upgrade?sellerId=1&planId=3
     */
    @PostMapping("/upgrade")
    public ResponseEntity<String> upgradeSubscription(
            @RequestParam Long sellerId,
            @RequestParam Long planId) {

        String response =
                subscriptionService.upgradeSubscription(sellerId, planId);

        return ResponseEntity.ok(response);

    }

}