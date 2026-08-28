package com.indusmart.controller;

import com.indusmart.email.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@CrossOrigin(origins = "*")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    /**
     * Send Test Email
     */
    @PostMapping("/test")
    public ResponseEntity<String> sendTestEmail(
            @RequestParam String email) {

        emailService.sendEmail(
                email,
                "IndusMart AI Test Email",
                "Congratulations! Your Email Configuration is working successfully."
        );

        return ResponseEntity.ok("Test Email Sent Successfully");
    }

    /**
     * Send Welcome Email
     */
    @PostMapping("/welcome")
    public ResponseEntity<String> sendWelcomeEmail(
            @RequestParam String email,
            @RequestParam String fullName) {

        emailService.sendWelcomeEmail(email, fullName);

        return ResponseEntity.ok("Welcome Email Sent Successfully");
    }

    /**
     * Send Order Confirmation
     */
    @PostMapping("/order")
    public ResponseEntity<String> sendOrderConfirmation(
            @RequestParam String email,
            @RequestParam String orderNumber) {

        emailService.sendOrderConfirmation(email, orderNumber);

        return ResponseEntity.ok("Order Confirmation Email Sent");
    }

    /**
     * Send Payment Success Email
     */
    @PostMapping("/payment")
    public ResponseEntity<String> sendPaymentSuccess(
            @RequestParam String email,
            @RequestParam String orderNumber) {

        emailService.sendPaymentSuccess(email, orderNumber);

        return ResponseEntity.ok("Payment Success Email Sent");
    }

    /**
     * Send Subscription Email
     */
    @PostMapping("/subscription")
    public ResponseEntity<String> sendSubscriptionEmail(
            @RequestParam String email,
            @RequestParam String planName) {

        emailService.sendSubscriptionEmail(email, planName);

        return ResponseEntity.ok("Subscription Email Sent");
    }

}