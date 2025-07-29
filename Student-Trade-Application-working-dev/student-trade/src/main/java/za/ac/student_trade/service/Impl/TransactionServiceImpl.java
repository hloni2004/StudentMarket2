package za.ac.student_trade.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.student_trade.domain.Product;
import za.ac.student_trade.domain.Student;
import za.ac.student_trade.domain.Transaction;
import za.ac.student_trade.repository.ProductRepository;
import za.ac.student_trade.repository.StudentRepository;
import za.ac.student_trade.repository.TransactionRepository;
import za.ac.student_trade.service.ITransactionService;

import java.time.LocalDateTime;
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
    public Transaction createTransaction(Transaction transaction, Long productSoldId, String buyerId) {

        Product productSold = productRepository.findById(productSoldId).get();

        Student buyer = studentRepository.findById(buyerId).get();

        Transaction newTransaction = new Transaction.Builder()
                .setTransactionId(UUID.randomUUID().toString())
                .setTransactionDate(LocalDateTime.now())
                .setImageOfProduct(productSold.getProductImageUrl())
                .setProductLabel(productSold.getProductName())
                .setProductDescription(productSold.getProductDescription())
                .setProductCondition(productSold.getCondition())
                .setPrice(productSold.getPrice())
                .setProduct(productSold)
                .setBuyer(buyer)
                .build();

        return this.transactionRepository.save(newTransaction);
    }

    @Override
    public Transaction create(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction read(String id) {
        return transactionRepository.findById(Long.valueOf(id)).get();
    }

    @Override
    public Transaction update(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
