package za.ac.student_trade.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    // Email to Seller (with product image)
    public void sendPurchaseNotification(
            String toEmail,
            String productName,
            double price,
            String buyerName,
            String buyerEmail,
            byte[] imageBytes,
            String imageType
    ) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("noreply@studenttrade.com");
        helper.setTo(toEmail);
        helper.setSubject("🎉 Your product '" + productName + "' has been sold!");

        String htmlContent = String.format(
                "<h2>Congratulations! 🎉</h2>" +
                        "<p>Your product has been purchased.</p>" +
                        "<p><b>Product Name:</b> %s<br>" +
                        "<b>Sale Price:</b> R%.2f</p>" +
                        "<p><b>Buyer Information:</b><br>" +
                        "Name: %s<br>" +
                        "Email: %s</p>" +
                        "<p><img src='cid:productImage' style='max-width:300px; border-radius:8px;' /></p>" +
                        "<p>Please contact the buyer to arrange pickup/delivery.</p>" +
                        "<p>Thank you for using Student Trade Marketplace!</p>",
                productName, price, buyerName, buyerEmail
        );

        helper.setText(htmlContent, true);

        // attach product image (inline)
        if (imageBytes != null && imageBytes.length > 0) {
            helper.addInline(
                    "productImage",
                    new ByteArrayResource(imageBytes),
                    imageType != null ? imageType : "image/png"
            );
        }

        mailSender.send(message);
    }

    // Email to Buyer (with product image)
    public void sendPurchaseConfirmation(
            String toEmail,
            String productName,
            double price,
            String sellerName,
            String sellerEmail,
            byte[] imageBytes,
            String imageType
    ) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("noreply@studenttrade.com");
        helper.setTo(toEmail);
        helper.setSubject("Purchase Confirmation: " + productName);

        String htmlContent = String.format(
                "<h2>Thank you for your purchase! </h2>" +
                        "<p><b>Product Name:</b> %s<br>" +
                        "<b>Price:</b> R%.2f</p>" +
                        "<p><b>Seller Information:</b><br>" +
                        "Name: %s<br>" +
                        "Email: %s</p>" +
                        "<p><img src='cid:productImage' style='max-width:300px; border-radius:8px;' /></p>" +
                        "<p>Please contact the seller to arrange pickup/delivery.</p>" +
                        "<p>Happy trading!</p>",
                productName, price, sellerName, sellerEmail
        );

        helper.setText(htmlContent, true);

        if (imageBytes != null && imageBytes.length > 0) {
            helper.addInline(
                    "productImage",
                    new ByteArrayResource(imageBytes),
                    imageType != null ? imageType : "image/png"
            );
        }

        mailSender.send(message);
    }
}
