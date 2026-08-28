package com.indusmart.pdf;

import com.indusmart.entity.Invoice;
import com.indusmart.exception.ResourceNotFoundException;
import com.indusmart.repository.InvoiceRepository;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;

@Service
public class InvoicePdfService {

    private final InvoiceRepository invoiceRepository;

    public InvoicePdfService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    /**
     * Generate Invoice PDF
     */
    public byte[] generateInvoicePdf(Long invoiceId) {

        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Invoice not found"));

        try {

            ByteArrayOutputStream outputStream =
                    new ByteArrayOutputStream();

            Document document = new Document(PageSize.A4);

            PdfWriter.getInstance(document, outputStream);

            document.open();

            Font titleFont = new Font(
                    Font.HELVETICA,
                    22,
                    Font.BOLD,
                    Color.BLUE
            );

            Font headingFont = new Font(
                    Font.HELVETICA,
                    14,
                    Font.BOLD
            );

            Font normalFont = new Font(
                    Font.HELVETICA,
                    12,
                    Font.NORMAL
            );

            Paragraph title =
                    new Paragraph("INDUSMART AI", titleFont);

            title.setAlignment(Element.ALIGN_CENTER);

            document.add(title);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(
                    "Industrial Equipment Marketplace",
                    headingFont));

            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(2);

            table.setWidthPercentage(100);

            addRow(table, "Invoice Number",
                    invoice.getInvoiceNumber());

            addRow(table, "Invoice Date",
                    invoice.getInvoiceDate().toString());

            addRow(table, "Buyer",
                    invoice.getBuyer().getFullName());

            addRow(table, "Email",
                    invoice.getBuyer().getEmail());

            addRow(table, "Order Number",
                    invoice.getOrder().getOrderNumber());

            addRow(table, "Payment Status",
                    invoice.getPaymentStatus());

            addRow(table, "Amount",
                    "₹ " + invoice.getTotalAmount());

            addRow(table, "GST",
                    invoice.getGstPercentage() + "%");

            addRow(table, "GST Amount",
                    "₹ " + invoice.getGstAmount());

            addRow(table, "Final Amount",
                    "₹ " + invoice.getFinalAmount());

            document.add(table);

            document.add(new Paragraph(" "));

            Paragraph footer = new Paragraph(
                    "Thank you for choosing IndusMart AI.",
                    normalFont);

            footer.setAlignment(Element.ALIGN_CENTER);

            document.add(footer);

            document.close();

            return outputStream.toByteArray();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to generate PDF",
                    e
            );

        }
    }

    /**
     * Table Row
     */
    private void addRow(
            PdfPTable table,
            String key,
            String value) {

        PdfPCell keyCell =
                new PdfPCell(new Phrase(key));

        PdfPCell valueCell =
                new PdfPCell(new Phrase(value));

        table.addCell(keyCell);
        table.addCell(valueCell);
    }

}