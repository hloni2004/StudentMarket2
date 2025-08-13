package za.ac.student_trade.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.student_trade.domain.Product;
import za.ac.student_trade.service.Impl.ProductServiceImpl;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductServiceImpl productService;

    @Autowired
    public void setProductService(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product) {
        return this.productService.create(product);
    }

    @GetMapping("/read/{id}")
    public Product readProduct(@PathVariable Long id) {
        return this.productService.read(id);
    }

    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts() {
        return this.productService.getAll();
    }
}
