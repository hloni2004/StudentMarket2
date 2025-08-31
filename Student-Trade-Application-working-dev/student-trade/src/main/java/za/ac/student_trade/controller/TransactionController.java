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
public class TransactionController {

    private TransactionServiceImpl transactionService;

    @Autowired
    public TransactionController(TransactionServiceImpl transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping( "/create")
    public Transaction createTransaction( @RequestParam Long productId,
                                         @RequestParam String buyerId) {
        return this.transactionService.createTransaction(new Transaction(),productId,buyerId);
    }

    @GetMapping("/read/{id}")
    public Transaction readTransaction(@PathVariable String id) {
        return this.transactionService.read(id);
    }
}
