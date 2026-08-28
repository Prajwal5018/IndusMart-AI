package com.indusmart.service;

import com.indusmart.entity.Invoice;
import com.indusmart.entity.Order;
import com.indusmart.exception.BadRequestException;
import com.indusmart.exception.ResourceNotFoundException;
import com.indusmart.repository.InvoiceRepository;
import com.indusmart.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class InvoiceService {

    private static final BigDecimal GST_PERCENT =
            BigDecimal.valueOf(18);

    private final InvoiceRepository invoiceRepository;
    private final OrderRepository orderRepository;

    public InvoiceService(
            InvoiceRepository invoiceRepository,
            OrderRepository orderRepository) {

        this.invoiceRepository = invoiceRepository;
        this.orderRepository = orderRepository;
    }

    /**
     * Generate Invoice
     */
    public Invoice generateInvoice(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order not found"));

        if (!"SUCCESS".equalsIgnoreCase(order.getPaymentStatus())) {
            throw new BadRequestException(
                    "Invoice can only be generated after successful payment."
            );
        }

        Invoice existingInvoice = invoiceRepository.findByOrderId(orderId)
                .orElse(null);

        if (existingInvoice != null) {
            return existingInvoice;
        }

        BigDecimal gstAmount = order.getTotalAmount()
                .multiply(GST_PERCENT)
                .divide(
                        BigDecimal.valueOf(100),
                        2,
                        RoundingMode.HALF_UP
                );

        BigDecimal finalAmount =
                order.getTotalAmount().add(gstAmount);

        Invoice invoice = Invoice.builder()

                .invoiceNumber(
                        "INV-"
                                + UUID.randomUUID()
                                .toString()
                                .substring(0, 8)
                                .toUpperCase()
                )

                .order(order)

                .buyer(order.getBuyer())

                .totalAmount(order.getTotalAmount())

                .gstPercentage(18.0)

                .gstAmount(gstAmount)

                .finalAmount(finalAmount)

                .paymentStatus(order.getPaymentStatus())

                .invoiceDate(LocalDateTime.now())

                .build();

        return invoiceRepository.save(invoice);
    }

    /**
     * Get Invoice By Order ID
     */
    public Invoice getInvoiceByOrder(Long orderId) {

        return invoiceRepository.findByOrderId(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Invoice not found"
                        ));
    }

    /**
     * Get Invoice By Invoice Number
     */
    public Invoice getInvoiceByNumber(String invoiceNumber) {

        return invoiceRepository.findByInvoiceNumber(invoiceNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Invoice not found"
                        ));
    }

    /**
     * Buyer Invoice History
     */
    public List<Invoice> getBuyerInvoices(Long buyerId) {

        return invoiceRepository
                .findByBuyerIdOrderByInvoiceDateDesc(buyerId);

    }

    /**
     * Check Invoice Exists
     */
    public boolean invoiceExists(Long orderId) {

        return invoiceRepository
                .findByOrderId(orderId)
                .isPresent();

    }

    /**
     * Delete Invoice
     */
    public String deleteInvoice(Long invoiceId) {

        if (!invoiceRepository.existsById(invoiceId)) {

            throw new ResourceNotFoundException(
                    "Invoice not found"
            );

        }

        invoiceRepository.deleteById(invoiceId);

        return "Invoice Deleted Successfully";
    }

}