package com.indusmart.controller;

import com.indusmart.pdf.InvoicePdfService;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices/pdf")
@CrossOrigin(origins = "*")
public class InvoicePdfController {

    private final InvoicePdfService invoicePdfService;

    public InvoicePdfController(
            InvoicePdfService invoicePdfService) {

        this.invoicePdfService = invoicePdfService;
    }

    /**
     * Download Invoice PDF
     */
    @GetMapping("/{invoiceId}")
    public ResponseEntity<byte[]> downloadInvoice(
            @PathVariable Long invoiceId) {

        byte[] pdf =
                invoicePdfService.generateInvoicePdf(invoiceId);

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_PDF);

        headers.setContentDisposition(
                ContentDisposition
                        .attachment()
                        .filename("Invoice-" + invoiceId + ".pdf")
                        .build()
        );

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdf);
    }

}