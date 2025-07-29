package za.ac.student_trade.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.student_trade.domain.Product;
import za.ac.student_trade.service.Impl.ProductServiceImpl;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private static ProductServiceImpl productService;

    @Autowired
    public void setProductService(ProductServiceImpl productService) {
        ProductController.productService = productService;
    }

    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product) {
        return this.productService.create(product);
    }

    @GetMapping("/read/{id}")
    public Product readProduct(@PathVariable Long id) {
        return this.productService.read(id);
    }
}
