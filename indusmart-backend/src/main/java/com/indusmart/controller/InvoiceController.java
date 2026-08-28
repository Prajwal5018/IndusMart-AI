package com.indusmart.controller;

import com.indusmart.entity.Invoice;
import com.indusmart.service.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@CrossOrigin(origins = "*")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    /**
     * Generate Invoice
     */
    @PostMapping("/generate/{orderId}")
    public ResponseEntity<Invoice> generateInvoice(
            @PathVariable Long orderId) {

        return ResponseEntity.ok(
                invoiceService.generateInvoice(orderId)
        );
    }

    /**
     * Get Invoice By Order
     */
    @GetMapping("/order/{orderId}")
    public ResponseEntity<Invoice> getInvoiceByOrder(
            @PathVariable Long orderId) {

        return ResponseEntity.ok(
                invoiceService.getInvoiceByOrder(orderId)
        );
    }

    /**
     * Get Invoice By Invoice Number
     */
    @GetMapping("/number/{invoiceNumber}")
    public ResponseEntity<Invoice> getInvoiceByNumber(
            @PathVariable String invoiceNumber) {

        return ResponseEntity.ok(
                invoiceService.getInvoiceByNumber(invoiceNumber)
        );
    }

    /**
     * Get Buyer's Invoice History
     */
    @GetMapping("/buyer/{buyerId}")
    public ResponseEntity<List<Invoice>> getBuyerInvoices(
            @PathVariable Long buyerId) {

        return ResponseEntity.ok(
                invoiceService.getBuyerInvoices(buyerId)
        );
    }

    /**
     * Delete Invoice
     */
    @DeleteMapping("/{invoiceId}")
    public ResponseEntity<String> deleteInvoice(
            @PathVariable Long invoiceId) {

        return ResponseEntity.ok(
                invoiceService.deleteInvoice(invoiceId)
        );
    }

}