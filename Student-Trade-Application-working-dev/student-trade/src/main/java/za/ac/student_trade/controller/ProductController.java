package za.ac.student_trade.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.ac.student_trade.domain.Product;
import za.ac.student_trade.domain.StripeResponse;
import za.ac.student_trade.domain.Student;
import za.ac.student_trade.factory.ProductFactory;
import za.ac.student_trade.service.Impl.ProductServiceImpl;
import za.ac.student_trade.service.Impl.StripeServiceImpl;

import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/product")
public class ProductController {

    private ProductServiceImpl productService;

    @Autowired
    public void setProductService(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public ResponseEntity<Product> createProduct(@RequestPart Product product, @RequestPart MultipartFile productImage) {
        try {
            Product newProduct = productService.addProduct(product, productImage);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/buy")
    public ResponseEntity<byte[]> getProductImage(@RequestParam("productId") Long productId) {
        Product product = productService.getProductById(productId);
        byte[] imageFile = product.getImageData();

        return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType())).body(imageFile);
    }

    @GetMapping("/read/{id}")
    public Product readProduct(@PathVariable Long id) {
        return this.productService.read(id);
    }

    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts() {
        return this.productService.getAll();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

    //payment controller
    @Autowired
    private StripeServiceImpl stripeService;

    @PostMapping("/checkout/{productId}")
    public ResponseEntity<StripeResponse> checkoutProduct(@PathVariable Long productId) {
        // 1️⃣ Fetch the fully populated product from DB
        Product savedProduct = productService.getProductById(productId);

        // 2️⃣ Call Stripe service with the saved product
        StripeResponse response = stripeService.checkoutProduct(savedProduct);

        // 3️⃣ Return the response to frontend
        return ResponseEntity.ok(response);

    }

    @GetMapping("/available/{studentId}")
    public ResponseEntity<List<Product>> getAvailableProductsByStudent(@PathVariable String studentId) {
        List<Product> products = productService.getAvailableProductsByStudent(studentId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/sold/{studentId}")
    public ResponseEntity<List<Product>> getSoldProductsByStudent(@PathVariable String studentId) {
        List<Product> products = productService.getSoldProductsByStudent(studentId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @PutMapping(value = "/update/{productId}", consumes = "multipart/form-data")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId,
                                                 @RequestPart Product product,
                                                 @RequestPart(required = false) MultipartFile productImage) {
        try {
            // 🌟 FIX: Use the Builder's copy and set methods to create a new Product object
            // 🌟 that includes the correct productId from the path.
            Product productWithId = new Product.Builder()
                    .copy(product)          // Copy all fields from the request body DTO
                    .setProductId(productId) // Override the ID with the URL parameter
                    .build();

            // Pass the correctly constructed object to the service layer
            Product updatedProduct = productService.updateProductWithImage(productWithId, productImage);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}



