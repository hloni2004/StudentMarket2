package za.ac.student_trade.factory;

import za.ac.student_trade.domain.Product;
import za.ac.student_trade.domain.Student;
import za.ac.student_trade.domain.Transaction;
import za.ac.student_trade.util.Helper;

import java.time.LocalDateTime;
import java.util.Random;

public class TransactionFactory {
    public static Transaction createTransaction(LocalDateTime transactionDate,String imageOfProduct,String productLabel,String description, String condition, double price, Product productSold, Student buyer) {


        return new Transaction.Builder()

                .setTransactionDate(transactionDate)
                .setImageOfProduct(imageOfProduct)
                .setProductLabel(productLabel)
                .setProductCondition(description)
                .setProductCondition(condition)
                .setPrice(price)
                .setProduct(productSold)
                .setBuyer(buyer)
                .build();
    }
}
