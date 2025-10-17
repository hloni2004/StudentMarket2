package za.ac.student_trade.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.ac.student_trade.config.CustomUserPrincipal;
import za.ac.student_trade.domain.Product;
import za.ac.student_trade.domain.Role;
import za.ac.student_trade.domain.StripeResponse;
import za.ac.student_trade.service.Impl.ProductServiceImpl;
import za.ac.student_trade.service.Impl.StripeServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("api/product")
public class ProductController {

    private ProductServiceImpl productService;

    //payment controller
    @Autowired
    private StripeServiceImpl stripeService;

    @Autowired
    public void setProductService(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/create", consumes = "multipart/form-data")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> createProduct(@RequestPart Product product, @RequestPart MultipartFile productImage) {
        try {
            System.out.println("ProductController: CREATE request received");
            
            // Backend security validation - ensure authenticated user is a student
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            System.out.println("ProductController: Authentication object: " + auth);
            
            if (auth == null || !(auth.getPrincipal() instanceof CustomUserPrincipal)) {
                System.out.println("ProductController: Authentication failed - no valid principal");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(createErrorResponse("Authentication required"));
            }

            CustomUserPrincipal userPrincipal = (CustomUserPrincipal) auth.getPrincipal();
            System.out.println("ProductController: User authenticated - " + userPrincipal.getUsername() + 
                             ", Role: " + userPrincipal.getRole() + ", UserID: " + userPrincipal.getUserId());
            
            // Critical security check: Ensure only students can create products
            if (userPrincipal.getRole() != Role.STUDENT) {
                System.out.println("ProductController: Access denied - user is not a student");
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(createErrorResponse("Only students can create products"));
            }

            // Log product data for debugging
            System.out.println("ProductController: Product data - Name: " + product.getProductName() + 
                             ", Price: " + product.getPrice() + ", Seller: " + 
                             (product.getSeller() != null ? product.getSeller().getStudentId() : "null"));

            // Security validation: Verify the product seller matches the authenticated user
            if (product.getSeller() == null || 
                !userPrincipal.getUserId().equals(product.getSeller().getStudentId())) {
                System.out.println("ProductController: Seller validation failed - " +
                                 "Expected: " + userPrincipal.getUserId() + 
                                 ", Got: " + (product.getSeller() != null ? product.getSeller().getStudentId() : "null"));
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(createErrorResponse("Cannot create product for another user"));
            }

            // Additional validation: Check product data integrity
            if (product.getProductName() == null || product.getProductName().trim().isEmpty()) {
                System.out.println("ProductController: Product name validation failed");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(createErrorResponse("Product name is required"));
            }

            if (product.getPrice() == null || product.getPrice() <= 0) {
                System.out.println("ProductController: Product price validation failed");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(createErrorResponse("Valid price is required"));
            }

            System.out.println("ProductController: All validations passed, creating product");
            Product newProduct = productService.addProduct(product, productImage);
            System.out.println("ProductController: Product created successfully with ID: " + newProduct.getProductId());
            
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
            
        } catch (Exception e) {
            System.err.println("ProductController: Error creating product: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(createErrorResponse("Failed to create product: " + e.getMessage()));
        }
    }

    @GetMapping("/buy")
    public ResponseEntity<byte[]> getProductImage(@RequestParam("productId") Long productId){
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
        return productService.getAll()
                .stream()
                .filter(product -> product.isAvailabilityStatus() && product.getTransaction() == null)
                .toList();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            // Backend security validation - ensure authenticated user has admin privileges
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth == null || !(auth.getPrincipal() instanceof CustomUserPrincipal)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(createErrorResponse("Authentication required"));
            }

            CustomUserPrincipal userPrincipal = (CustomUserPrincipal) auth.getPrincipal();
            
            // Critical security check: Ensure only admins can delete products
            if (userPrincipal.getRole() != Role.ADMIN && userPrincipal.getRole() != Role.SUPER_ADMIN) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(createErrorResponse("Insufficient privileges to delete products"));
            }

            // Verify product exists before deletion
            Product existingProduct = productService.read(id);
            if (existingProduct == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(createErrorResponse("Product not found"));
            }

            productService.delete(id);
            return ResponseEntity.ok(createSuccessResponse("Product deleted successfully"));
            
        } catch (Exception e) {
            System.err.println("Error deleting product: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(createErrorResponse("Failed to delete product"));
        }
    }

    @PostMapping("/checkout/{productId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> checkoutProduct(@PathVariable Long productId) {
        try {
            // Backend security validation - ensure authenticated user is a student
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth == null || !(auth.getPrincipal() instanceof CustomUserPrincipal)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(createErrorResponse("Authentication required"));
            }

            CustomUserPrincipal userPrincipal = (CustomUserPrincipal) auth.getPrincipal();
            
            // Critical security check: Ensure only students can make purchases
            if (userPrincipal.getRole() != Role.STUDENT) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(createErrorResponse("Only students can make purchases"));
            }

            // 1️⃣ Fetch the fully populated product from DB
            Product savedProduct = productService.getProductById(productId);
            
            if (savedProduct == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(createErrorResponse("Product not found"));
            }

            // Security check: Prevent self-purchase
            if (savedProduct.getSeller() != null && 
                userPrincipal.getUserId().equals(savedProduct.getSeller().getStudentId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(createErrorResponse("Cannot purchase your own product"));
            }

            // 2️⃣ Call Stripe service with the saved product
            StripeResponse response = stripeService.checkoutProduct(savedProduct);

            // 3️⃣ Return the response to frontend
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            System.err.println("Error processing checkout: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(createErrorResponse("Failed to process checkout"));
        }
    }

    // Helper methods for response formatting
    private Map<String, Object> createErrorResponse(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", message);
        return response;
    }

    private Map<String, Object> createSuccessResponse(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", message);
        return response;
    }

}
