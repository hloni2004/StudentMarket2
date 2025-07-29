package za.ac.student_trade.service;

import za.ac.student_trade.domain.Product;

import java.util.List;

public interface IProductService extends IService<Product, Long>{
    Product createProduct(Product product, String sellerId);

    List<Product> getAllProducts();
}
