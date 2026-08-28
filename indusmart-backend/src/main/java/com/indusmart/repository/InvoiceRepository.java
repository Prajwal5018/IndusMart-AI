package com.indusmart.repository;

import com.indusmart.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    /**
     * Find Invoice By Invoice Number
     */
    Optional<Invoice> findByInvoiceNumber(String invoiceNumber);

    /**
     * Find Invoice By Order ID
     */
    Optional<Invoice> findByOrderId(Long orderId);

    /**
     * Find All Invoices Of Buyer
     */
    List<Invoice> findByBuyerIdOrderByInvoiceDateDesc(Long buyerId);

    /**
     * Check Invoice Exists
     */
    boolean existsByInvoiceNumber(String invoiceNumber);

}