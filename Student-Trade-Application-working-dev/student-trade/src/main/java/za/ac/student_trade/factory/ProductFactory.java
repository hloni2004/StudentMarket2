package za.ac.student_trade.factory;

import za.ac.student_trade.domain.Product;
import za.ac.student_trade.domain.Student;

import java.util.Random;

public class ProductFactory {

    public static Product create(String productName, String productDescription, String condition, Double price, String productCategory,
                                 boolean availabilityStatus, String productImageUrl, Student seller) {

        return new Product.Builder()
                .setProductName(productName)
                .setProductDescription(productDescription)
                .setCondition(condition)
                .setPrice(price)
                .setProductCategory(productCategory)
                .setAvailabilityStatus(availabilityStatus)
                .setProductImageUrl(productImageUrl)
                .setSeller(seller)
                .build();
    };
}
