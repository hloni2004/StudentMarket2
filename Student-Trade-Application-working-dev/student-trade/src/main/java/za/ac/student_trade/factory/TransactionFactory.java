package za.ac.student_trade.factory;

import za.ac.student_trade.domain.Product;
import za.ac.student_trade.domain.Student;
import za.ac.student_trade.domain.Transaction;
import za.ac.student_trade.domain.Transaction.TransactionStatus;

import java.time.LocalDateTime;

public class TransactionFactory {

    public static Transaction createTransaction(Product product, Student buyer) {
        // Get the seller from the product
        Student seller = product.getSeller();

        return new Transaction.Builder()
                .setPrice(product.getPrice())
                .setProduct(product)
                .setBuyer(buyer)
                .setSeller(seller)
                .setStatus(TransactionStatus.COMPLETED)
                .build();
    }

    public static Transaction createTransaction(LocalDateTime transactionDate, byte[] imageOfProduct,String productLabel ,
                                                String description, String condition ,double price,
                                                TransactionStatus status, Product product,
                                                Student buyer, Student seller) {

        return new Transaction.Builder()
                .setTransactionDate(transactionDate)
                .setImageOfProduct(imageOfProduct)
                .setProductLabel(productLabel)
                .setProductDescription(description)
                .setProductCondition(condition)
                .setPrice(price)
                .setStatus(status)
                .setProduct(product)
                .setBuyer(buyer)
                .setSeller(seller)
                .build();
    }
}