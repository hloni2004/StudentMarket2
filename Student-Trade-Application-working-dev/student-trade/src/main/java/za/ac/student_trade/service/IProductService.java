package za.ac.student_trade.service;

import za.ac.student_trade.domain.Product;
import za.ac.student_trade.domain.Student;

import java.util.List;

public interface IProductService extends IService<Product, Long>{
    Product createProduct(Product product, Student seller);
}
