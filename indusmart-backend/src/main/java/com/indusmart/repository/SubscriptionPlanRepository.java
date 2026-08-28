package com.indusmart.repository;

import com.indusmart.entity.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan, Long> {

    /**
     * Find plan by name
     * Example:
     * BASIC
     * PROFESSIONAL
     * PREMIUM
     */
    Optional<SubscriptionPlan> findByPlanName(String planName);

    /**
     * Check if plan already exists
     */
    boolean existsByPlanName(String planName);

}