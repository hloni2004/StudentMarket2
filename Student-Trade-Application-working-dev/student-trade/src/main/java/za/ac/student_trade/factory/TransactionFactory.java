package za.ac.student_trade.factory;

import za.ac.student_trade.domain.Product;
import za.ac.student_trade.domain.Student;
import za.ac.student_trade.domain.Transaction;

import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionFactory {

    public static Transaction createTransaction(
            LocalDateTime transactionDate,
            byte[] imageOfProduct,
            String productLabel,
            String description,
            String condition,
            double price,
            Product productSold,
            Student buyer
    ) {
        String transactionId = UUID.randomUUID().toString();

        return new Transaction.Builder()
                .setTransactionId(transactionId)
                .setTransactionDate(transactionDate)
                .setImageOfProduct(imageOfProduct)
                .setProductLabel(productLabel)
                .setProductDescription(description)  // <-- fix: use description
                .setProductCondition(condition)      // <-- correct: condition
                .setPrice(price)
                .setProduct(productSold)
                .setBuyer(buyer)
                .build();
    }
}
