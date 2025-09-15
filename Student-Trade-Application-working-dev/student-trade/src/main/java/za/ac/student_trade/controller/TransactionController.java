package za.ac.student_trade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.ac.student_trade.domain.Product;
import za.ac.student_trade.domain.Transaction;
import za.ac.student_trade.service.Impl.TransactionServiceImpl;

import java.io.IOException;

@RestController
@RequestMapping("/api/transaction")
@CrossOrigin(origins = "http://localhost:3000")
public class TransactionController {

    private final TransactionServiceImpl transactionService;

    @Autowired
    public TransactionController(TransactionServiceImpl transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/create")
    public Transaction createTransaction(@RequestParam Long productId,
                                         @RequestParam String buyerId) {
        return transactionService.createTransaction(null, productId, buyerId);
    }

    @GetMapping("/{id}")  // cleaner REST style
    public Transaction readTransaction(@PathVariable String id) {
        return transactionService.read(id);
    }
}
