package com.indusmart.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Send Simple Email
     */
    public void sendEmail(
            String to,
            String subject,
            String body) {

        SimpleMailMessage message =
                new SimpleMailMessage();

        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    /**
     * Welcome Email
     */
    public void sendWelcomeEmail(
            String email,
            String fullName) {

        String subject =
                "Welcome to IndusMart AI";

        String body =
                "Hello " + fullName + ",\n\n"
                        + "Welcome to IndusMart AI.\n\n"
                        + "Your account has been created successfully.\n\n"
                        + "Thank you for joining us.\n\n"
                        + "Regards,\n"
                        + "IndusMart AI Team";

        sendEmail(email, subject, body);
    }

    /**
     * Order Confirmation
     */
    public void sendOrderConfirmation(
            String email,
            String orderNumber) {

        String subject =
                "Order Confirmation";

        String body =
                "Your order "
                        + orderNumber
                        + " has been placed successfully.";

        sendEmail(email, subject, body);
    }

    /**
     * Payment Success
     */
    public void sendPaymentSuccess(
            String email,
            String orderNumber) {

        String subject =
                "Payment Successful";

        String body =
                "Payment received successfully for Order "
                        + orderNumber
                        + ".";

        sendEmail(email, subject, body);
    }

    /**
     * Subscription Purchased
     */
    public void sendSubscriptionEmail(
            String email,
            String planName) {

        String subject =
                "Subscription Activated";

        String body =
                "Congratulations!\n\n"
                        + "Your "
                        + planName
                        + " subscription has been activated successfully.";

        sendEmail(email, subject, body);
    }

    /**
     * Invoice Email With PDF Attachment
     */
    public void sendInvoiceEmail(
            String email,
            String invoiceNumber,
            byte[] pdfFile)
            throws MessagingException {

        MimeMessage mimeMessage =
                mailSender.createMimeMessage();

        MimeMessageHelper helper =
                new MimeMessageHelper(
                        mimeMessage,
                        true
                );

        helper.setTo(email);

        helper.setSubject(
                "Invoice - " + invoiceNumber
        );

        helper.setText(
                "Please find your invoice attached."
        );

        helper.addAttachment(
                invoiceNumber + ".pdf",
                new ByteArrayResource(pdfFile)
        );

        mailSender.send(mimeMessage);
    }

}