package za.ac.student_trade.service.Impl;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.stereotype.Service;
import za.ac.student_trade.domain.Transaction;

import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class InvoiceService {

    public byte[] generateInvoicePDF(Transaction transaction) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Custom colors
        DeviceRgb primaryColor = new DeviceRgb(41, 128, 185);
        DeviceRgb lightGray = new DeviceRgb(236, 240, 241);

        // Header Section
        addHeader(document, primaryColor);
        document.add(new Paragraph("\n"));

        // Invoice Title
        Paragraph invoiceTitle = new Paragraph("PURCHASE INVOICE")
                .setFontSize(24)
                .setBold()
                .setFontColor(primaryColor)
                .setTextAlignment(TextAlignment.CENTER);
        document.add(invoiceTitle);
        document.add(new Paragraph("\n"));

        // Transaction Details Box
        addTransactionDetails(document, transaction, lightGray);
        document.add(new Paragraph("\n"));

        // Buyer and Seller Information
        addPartyInformation(document, transaction, lightGray);
        document.add(new Paragraph("\n"));

        // Product Details Table
        addProductTable(document, transaction, primaryColor, lightGray);
        document.add(new Paragraph("\n"));

        // Product Image (if available)
        if (transaction.getImageOfProduct() != null && transaction.getImageOfProduct().length > 0) {
            addProductImage(document, transaction);
        }

        // Payment Summary
        addPaymentSummary(document, transaction, primaryColor);
        document.add(new Paragraph("\n"));

        // Footer
        addFooter(document, primaryColor);

        document.close();
        return baos.toByteArray();
    }

    private void addHeader(Document document, DeviceRgb color) {
        Paragraph header = new Paragraph("Student Trade Marketplace")
                .setFontSize(28)
                .setBold()
                .setFontColor(color)
                .setTextAlignment(TextAlignment.CENTER);
        document.add(header);

        Paragraph tagline = new Paragraph("Buy. Sell. Trade. Connect.")
                .setFontSize(12)
                .setFontColor(ColorConstants.GRAY)
                .setTextAlignment(TextAlignment.CENTER);
        document.add(tagline);

        // Divider line
        LineSeparator ls = new LineSeparator(new com.itextpdf.kernel.pdf.canvas.draw.SolidLine());
        ls.setStrokeColor(color);
        document.add(ls);
    }

    private void addTransactionDetails(Document document, Transaction transaction, DeviceRgb bgColor) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm");

        Table detailsTable = new Table(2);
        detailsTable.setWidth(UnitValue.createPercentValue(100));
        detailsTable.setBackgroundColor(bgColor);

        detailsTable.addCell(createCell("Transaction ID:", true));
        detailsTable.addCell(createCell(transaction.getTransactionId(), false));

        detailsTable.addCell(createCell("Date & Time:", true));
        detailsTable.addCell(createCell(
                transaction.getTransactionDate().format(formatter), false));

        detailsTable.addCell(createCell("Status:", true));
        detailsTable.addCell(createCell(transaction.getStatus().toString(), false));

        document.add(detailsTable);
    }

    private void addPartyInformation(Document document, Transaction transaction, DeviceRgb bgColor) {
        Table partyTable = new Table(2);
        partyTable.setWidth(UnitValue.createPercentValue(100));

        // Buyer Information
        Cell buyerCell = new Cell();
        buyerCell.setBackgroundColor(bgColor);
        buyerCell.setPadding(10);

        buyerCell.add(new Paragraph("BUYER INFORMATION")
                .setBold()
                .setFontSize(12));
        buyerCell.add(new Paragraph("Name: " +
                transaction.getBuyer().getFirstName() + " " +
                transaction.getBuyer().getLastName())
                .setFontSize(10));
        buyerCell.add(new Paragraph("Student ID: " +
                transaction.getBuyer().getStudentId())
                .setFontSize(10));
        buyerCell.add(new Paragraph("Email: " +
                transaction.getBuyer().getEmail())
                .setFontSize(10));

        // Seller Information
        Cell sellerCell = new Cell();
        sellerCell.setBackgroundColor(bgColor);
        sellerCell.setPadding(10);

        sellerCell.add(new Paragraph("SELLER INFORMATION")
                .setBold()
                .setFontSize(12));
        sellerCell.add(new Paragraph("Name: " +
                transaction.getSeller().getFirstName() + " " +
                transaction.getSeller().getLastName())
                .setFontSize(10));
        sellerCell.add(new Paragraph("Student ID: " +
                transaction.getSeller().getStudentId())
                .setFontSize(10));
        sellerCell.add(new Paragraph("Email: " +
                transaction.getSeller().getEmail())
                .setFontSize(10));

        partyTable.addCell(buyerCell);
        partyTable.addCell(sellerCell);

        document.add(partyTable);
    }

    private void addProductTable(Document document, Transaction transaction,
                                 DeviceRgb headerColor, DeviceRgb altRowColor) {
        Table productTable = new Table(new float[]{3, 5, 2, 2});
        productTable.setWidth(UnitValue.createPercentValue(100));

        // Header
        productTable.addHeaderCell(createHeaderCell("Item", headerColor));
        productTable.addHeaderCell(createHeaderCell("Description", headerColor));
        productTable.addHeaderCell(createHeaderCell("Condition", headerColor));
        productTable.addHeaderCell(createHeaderCell("Price", headerColor));

        // Product Row
        productTable.addCell(createCell(transaction.getProductLabel(), false));
        productTable.addCell(createCell(transaction.getProductDescription(), false));
        productTable.addCell(createCell(transaction.getProductCondition(), false));
        productTable.addCell(createCell(formatCurrency(transaction.getPrice()), false));

        document.add(productTable);
    }

    private void addProductImage(Document document, Transaction transaction) {
        try {
            Image img = new Image(ImageDataFactory.create(transaction.getImageOfProduct()));
            img.setWidth(200);
            img.setHeight(200);
            img.setHorizontalAlignment(com.itextpdf.layout.properties.HorizontalAlignment.CENTER);

            Paragraph imgCaption = new Paragraph("Product Image")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(10)
                    .setFontColor(ColorConstants.GRAY);

            document.add(imgCaption);
            document.add(img);
        } catch (Exception e) {
            System.err.println("Could not add product image: " + e.getMessage());
        }
    }

    private void addPaymentSummary(Document document, Transaction transaction, DeviceRgb color) {
        Table summaryTable = new Table(new float[]{3, 1});
        summaryTable.setWidth(UnitValue.createPercentValue(100));

        // Subtotal
        summaryTable.addCell(createCell("Subtotal:", true).setTextAlignment(TextAlignment.RIGHT));
        summaryTable.addCell(createCell(formatCurrency(transaction.getPrice()), false)
                .setTextAlignment(TextAlignment.RIGHT));

        // Platform Fee (example - 5%)
        double platformFee = transaction.getPrice() * 0.05;
        summaryTable.addCell(createCell("Platform Fee (5%):", true).setTextAlignment(TextAlignment.RIGHT));
        summaryTable.addCell(createCell(formatCurrency(platformFee), false)
                .setTextAlignment(TextAlignment.RIGHT));

        // Total
        Cell totalLabelCell = createCell("TOTAL AMOUNT:", true)
                .setTextAlignment(TextAlignment.RIGHT)
                .setFontSize(14)
                .setBold()
                .setFontColor(color);

        Cell totalValueCell = createCell(formatCurrency(transaction.getPrice() + platformFee), false)
                .setTextAlignment(TextAlignment.RIGHT)
                .setFontSize(14)
                .setBold()
                .setFontColor(color);

        summaryTable.addCell(totalLabelCell);
        summaryTable.addCell(totalValueCell);

        document.add(summaryTable);
    }

    private void addFooter(Document document, DeviceRgb color) {
        LineSeparator ls = new LineSeparator(new com.itextpdf.kernel.pdf.canvas.draw.SolidLine());
        ls.setStrokeColor(color);
        document.add(ls);

        Paragraph footer = new Paragraph(
                "Thank you for using Student Trade Marketplace!\n" +
                        "For support, contact: support@studenttrade.ac.za\n" +
                        "This is an automated invoice. No signature required.")
                .setFontSize(9)
                .setFontColor(ColorConstants.GRAY)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginTop(10);
        document.add(footer);
    }

    private Cell createCell(String content, boolean isBold) {
        Cell cell = new Cell();
        Paragraph p = new Paragraph(content).setFontSize(10);
        if (isBold) {
            p.setBold();
        }
        cell.add(p);
        cell.setPadding(5);
        cell.setBorder(null);
        return cell;
    }

    private Cell createHeaderCell(String content, DeviceRgb bgColor) {
        Cell cell = new Cell();
        cell.add(new Paragraph(content)
                .setBold()
                .setFontSize(11)
                .setFontColor(ColorConstants.WHITE));
        cell.setBackgroundColor(bgColor);
        cell.setPadding(8);
        cell.setTextAlignment(TextAlignment.CENTER);
        return cell;
    }

    private String formatCurrency(double amount) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "ZA"));
        return formatter.format(amount);
    }
}