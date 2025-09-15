package za.ac.student_trade.service.Impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import za.ac.student_trade.domain.Product;
import za.ac.student_trade.domain.Student;
import za.ac.student_trade.domain.Transaction;
import za.ac.student_trade.factory.TransactionFactory;
import za.ac.student_trade.repository.ProductRepository;
import za.ac.student_trade.repository.StudentRepository;
import za.ac.student_trade.repository.TransactionRepository;
import za.ac.student_trade.service.ITransactionService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements ITransactionService {

    private TransactionRepository transactionRepository;
    private ProductRepository productRepository;
    private StudentRepository studentRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, ProductRepository productRepository, StudentRepository studentRepository) {
        this.transactionRepository = transactionRepository;
        this.productRepository = productRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional
    public Transaction createTransaction(Transaction transaction, Long productId, String buyerId) {

        Student buyer = studentRepository.findById(buyerId)
                .orElseThrow(() -> new RuntimeException("Buyer not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Prevent buying the same product twice
        boolean alreadySold = transactionRepository.existsByProduct(product);
        if (alreadySold) {
            throw new RuntimeException("Product is already sold");
        }

        // Create a new transaction using the factory
        Transaction newTransaction = TransactionFactory.createTransaction(
                LocalDateTime.now(),
                product.getImageData(),
                product.getProductName(),
                product.getProductDescription(),
                product.getCondition(),
                product.getPrice(),
                product,
                buyer
        );

        return transactionRepository.save(newTransaction);
    }



    @Override
    public Transaction create(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction read(String id) {
        return transactionRepository.findById(id).get();
    }

    @Override
    public Transaction update(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAll(){
        return transactionRepository.findAll();
    };

}
