package za.ac.student_trade.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.student_trade.domain.Product;
import za.ac.student_trade.domain.Student;
import za.ac.student_trade.factory.ProductFactory;
import za.ac.student_trade.repository.ProductRepository;
import za.ac.student_trade.repository.StudentRepository;
import za.ac.student_trade.service.IProductService;
import za.ac.student_trade.service.IService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    private ProductRepository productRepository;
    private StudentRepository studentRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, StudentRepository studentRepository) {
        this.productRepository = productRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Product createProduct(Product product, String sellerId) {
        Optional<Student> seller = studentRepository.findById(sellerId);

        Student currentSeller = seller.get();

        Product newProduct = ProductFactory.create(
                product.getProductName(),
                product.getProductDescription(),
                product.getCondition(),
                product.getPrice(),
                product.getProductCategory(),
                product.isAvailabilityStatus(),
                product.getProductImageUrl(),
                currentSeller
        );

        return productRepository.save(newProduct);
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product read(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }
}
