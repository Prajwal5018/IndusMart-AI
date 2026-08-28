package com.indusmart.service;

import com.indusmart.entity.SellerSubscription;
import com.indusmart.entity.SubscriptionPayment;
import com.indusmart.entity.SubscriptionPlan;
import com.indusmart.entity.User;
import com.indusmart.repository.SellerSubscriptionRepository;
import com.indusmart.repository.SubscriptionPaymentRepository;
import com.indusmart.repository.SubscriptionPlanRepository;
import com.indusmart.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SubscriptionService {

    private final SubscriptionPlanRepository subscriptionPlanRepository;
    private final SellerSubscriptionRepository sellerSubscriptionRepository;
    private final SubscriptionPaymentRepository subscriptionPaymentRepository;
    private final UserRepository userRepository;

    public SubscriptionService(
            SubscriptionPlanRepository subscriptionPlanRepository,
            SellerSubscriptionRepository sellerSubscriptionRepository,
            SubscriptionPaymentRepository subscriptionPaymentRepository,
            UserRepository userRepository) {

        this.subscriptionPlanRepository = subscriptionPlanRepository;
        this.sellerSubscriptionRepository = sellerSubscriptionRepository;
        this.subscriptionPaymentRepository = subscriptionPaymentRepository;
        this.userRepository = userRepository;
    }

    /**
     * Get all subscription plans
     */
    public List<SubscriptionPlan> getAllPlans() {
        return subscriptionPlanRepository.findAll();
    }

    /**
     * Buy Subscription
     */
    public String buySubscription(Long sellerId, Long planId) {

        Optional<User> sellerOptional =
                userRepository.findById(sellerId);

        if (sellerOptional.isEmpty()) {
            return "Seller not found";
        }

        User seller = sellerOptional.get();

        Optional<SubscriptionPlan> planOptional =
                subscriptionPlanRepository.findById(planId);

        if (planOptional.isEmpty()) {
            return "Subscription Plan not found";
        }

        SubscriptionPlan plan = planOptional.get();

        if (sellerSubscriptionRepository.existsBySellerId(sellerId)) {
            return "Seller already has an active subscription. Please upgrade or renew.";
        }

        SellerSubscription subscription =
                SellerSubscription.builder()
                        .seller(seller)
                        .subscriptionPlan(plan)
                        .startDate(LocalDate.now())
                        .expiryDate(LocalDate.now().plusDays(plan.getValidityDays()))
                        .productsUploaded(0)
                        .active(true)
                        .paymentId("PAY_" + UUID.randomUUID())
                        .orderId("ORDER_" + UUID.randomUUID())
                        .paymentSignature(UUID.randomUUID().toString())
                        .build();

        sellerSubscriptionRepository.save(subscription);

        SubscriptionPayment payment =
                SubscriptionPayment.builder()
                        .seller(seller)
                        .subscriptionPlan(plan)
                        .amount(plan.getPrice())
                        .razorpayOrderId(subscription.getOrderId())
                        .razorpayPaymentId(subscription.getPaymentId())
                        .razorpaySignature(subscription.getPaymentSignature())
                        .paymentMethod("TEST")
                        .paymentStatus("SUCCESS")
                        .paymentDate(LocalDateTime.now())
                        .invoiceNumber("INV-" + UUID.randomUUID())
                        .build();

        subscriptionPaymentRepository.save(payment);

        return "Subscription Purchased Successfully";
    }

    /**
     * Upgrade Subscription
     */
    public String upgradeSubscription(Long sellerId, Long newPlanId) {

        Optional<SellerSubscription> optionalSubscription =
                sellerSubscriptionRepository.findBySellerId(sellerId);

        if (optionalSubscription.isEmpty()) {
            return "No Active Subscription Found";
        }

        Optional<SubscriptionPlan> optionalPlan =
                subscriptionPlanRepository.findById(newPlanId);

        if (optionalPlan.isEmpty()) {
            return "Subscription Plan not found";
        }

        SellerSubscription subscription = optionalSubscription.get();

        SubscriptionPlan newPlan = optionalPlan.get();

        subscription.setSubscriptionPlan(newPlan);
        subscription.setStartDate(LocalDate.now());
        subscription.setExpiryDate(LocalDate.now().plusDays(newPlan.getValidityDays()));

        sellerSubscriptionRepository.save(subscription);

        SubscriptionPayment payment =
                SubscriptionPayment.builder()
                        .seller(subscription.getSeller())
                        .subscriptionPlan(newPlan)
                        .amount(newPlan.getPrice())
                        .razorpayOrderId("ORDER_" + UUID.randomUUID())
                        .razorpayPaymentId("PAY_" + UUID.randomUUID())
                        .razorpaySignature(UUID.randomUUID().toString())
                        .paymentMethod("TEST")
                        .paymentStatus("SUCCESS")
                        .paymentDate(LocalDateTime.now())
                        .invoiceNumber("INV-" + UUID.randomUUID())
                        .build();

        subscriptionPaymentRepository.save(payment);

        return "Subscription Upgraded Successfully";
    }
    /**
     * Renew Subscription
     */
    public String renewSubscription(Long sellerId) {

        Optional<SellerSubscription> optionalSubscription =
                sellerSubscriptionRepository.findBySellerId(sellerId);

        if (optionalSubscription.isEmpty()) {
            return "No Active Subscription Found";
        }

        SellerSubscription subscription = optionalSubscription.get();

        SubscriptionPlan plan = subscription.getSubscriptionPlan();

        subscription.setStartDate(LocalDate.now());
        subscription.setExpiryDate(LocalDate.now().plusDays(plan.getValidityDays()));
        subscription.setActive(true);

        sellerSubscriptionRepository.save(subscription);

        SubscriptionPayment payment =
                SubscriptionPayment.builder()
                        .seller(subscription.getSeller())
                        .subscriptionPlan(plan)
                        .amount(plan.getPrice())
                        .razorpayOrderId("ORDER_" + UUID.randomUUID())
                        .razorpayPaymentId("PAY_" + UUID.randomUUID())
                        .razorpaySignature(UUID.randomUUID().toString())
                        .paymentMethod("TEST")
                        .paymentStatus("SUCCESS")
                        .paymentDate(LocalDateTime.now())
                        .invoiceNumber("INV-" + UUID.randomUUID())
                        .build();

        subscriptionPaymentRepository.save(payment);

        return "Subscription Renewed Successfully";
    }

    /**
     * Get Current Subscription
     */
    public SellerSubscription getCurrentSubscription(Long sellerId) {

        return sellerSubscriptionRepository
                .findBySellerId(sellerId)
                .orElse(null);
    }

    /**
     * Check if Subscription is Active
     */
    public boolean isSubscriptionActive(Long sellerId) {

        Optional<SellerSubscription> optionalSubscription =
                sellerSubscriptionRepository.findBySellerId(sellerId);

        if (optionalSubscription.isEmpty()) {
            return false;
        }

        SellerSubscription subscription = optionalSubscription.get();

        if (!subscription.isActive()) {
            return false;
        }

        return !subscription.getExpiryDate().isBefore(LocalDate.now());
    }

    /**
     * Check if Seller Can Upload Product
     */
    public boolean canUploadProduct(Long sellerId) {

        Optional<SellerSubscription> optionalSubscription =
                sellerSubscriptionRepository.findBySellerId(sellerId);

        if (optionalSubscription.isEmpty()) {
            return false;
        }

        SellerSubscription subscription = optionalSubscription.get();

        if (!subscription.isActive()) {
            return false;
        }

        if (subscription.getExpiryDate().isBefore(LocalDate.now())) {
            return false;
        }

        int limit =
                subscription.getSubscriptionPlan().getProductLimit();

        if (limit == -1) {
            return true;
        }

        return subscription.getProductsUploaded() < limit;
    }

    /**
     * Increase Uploaded Product Count
     */
    public void incrementUploadedProducts(Long sellerId) {

        Optional<SellerSubscription> optionalSubscription =
                sellerSubscriptionRepository.findBySellerId(sellerId);

        if (optionalSubscription.isPresent()) {

            SellerSubscription subscription =
                    optionalSubscription.get();

            subscription.setProductsUploaded(
                    subscription.getProductsUploaded() + 1
            );

            sellerSubscriptionRepository.save(subscription);
        }
    }

    /**
     * Decrease Uploaded Product Count
     */
    public void decrementUploadedProducts(Long sellerId) {

        Optional<SellerSubscription> optionalSubscription =
                sellerSubscriptionRepository.findBySellerId(sellerId);

        if (optionalSubscription.isPresent()) {

            SellerSubscription subscription =
                    optionalSubscription.get();

            if (subscription.getProductsUploaded() > 0) {

                subscription.setProductsUploaded(
                        subscription.getProductsUploaded() - 1
                );

                sellerSubscriptionRepository.save(subscription);
            }
        }
    }

    /**
     * Remaining Product Upload Slots
     */
    public int getRemainingProductSlots(Long sellerId) {

        Optional<SellerSubscription> optionalSubscription =
                sellerSubscriptionRepository.findBySellerId(sellerId);

        if (optionalSubscription.isEmpty()) {
            return 0;
        }

        SellerSubscription subscription = optionalSubscription.get();

        int limit =
                subscription.getSubscriptionPlan().getProductLimit();

        if (limit == -1) {
            return Integer.MAX_VALUE;
        }

        return limit - subscription.getProductsUploaded();
    }

    /**
     * Get Payment History
     */
    public List<SubscriptionPayment> getPaymentHistory(Long sellerId) {

        return subscriptionPaymentRepository.findBySellerId(sellerId);
    }
}