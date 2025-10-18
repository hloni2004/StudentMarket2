package za.ac.student_trade.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import za.ac.student_trade.domain.Product;
import za.ac.student_trade.factory.ProductFactory;
import za.ac.student_trade.repository.ProductRepository;
import za.ac.student_trade.repository.StudentRepository;
import za.ac.student_trade.service.IProductService;

import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        return productRepository.save(
                ProductFactory.create(
                        product.getProductName(),
                        product.getProductDescription(),
                        product.getCondition(),
                        product.getPrice(),
                        product.getCurrency(),
                        product.getProductCategory(),
                        product.isAvailabilityStatus(),
                        imageFile.getOriginalFilename(),
                        imageFile.getContentType(),
                        imageFile.getBytes(),
                        product.getReleaseDate(),
                        product.getSeller()
                )
        );
    }

    @Override
    public Product getProductById(Long id){
        return productRepository.findById(id).get();
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
    public List<Product> getAll() {
        return productRepository.findAll();
    }
    public List<Product> getAvailableProductsByStudent(String studentId) {
        return productRepository.findBySellerStudentIdAndAvailabilityStatusTrue(studentId);
    }



    @Override
    public void delete(Long productId) {
        productRepository.deleteById(productId);
    }

    public List<Product> getSoldProductsByStudent(String studentId) {
        return productRepository.findBySellerStudentIdAndAvailabilityStatusFalse(studentId);
    }
    @Override
    public Product updateProductWithImage(Product updatedProduct, MultipartFile imageFile) throws IOException {
        // 1. Get the existing product from the database
        Product existingProduct = productRepository.findById(updatedProduct.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // 2. Use the Builder to copy the existing product, ensuring all fields are present
        Product.Builder builder = new Product.Builder().copy(existingProduct);

        // 3. Apply changes from the updatedProduct DTO
        builder.setProductName(updatedProduct.getProductName())
                .setProductDescription(updatedProduct.getProductDescription())
                .setPrice(updatedProduct.getPrice());



        if (imageFile != null && !imageFile.isEmpty()) {
            builder.setImageName(imageFile.getOriginalFilename())
                    .setImageType(imageFile.getContentType())
                    .setImageData(imageFile.getBytes());
        }
        Product finalProduct = builder.build();
        return productRepository.save(finalProduct);
    }




}
