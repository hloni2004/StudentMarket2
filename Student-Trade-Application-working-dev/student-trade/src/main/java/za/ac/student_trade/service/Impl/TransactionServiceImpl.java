package za.ac.student_trade.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.student_trade.domain.Product;
import za.ac.student_trade.domain.Student;
import za.ac.student_trade.domain.Transaction;
import za.ac.student_trade.repository.ProductRepository;
import za.ac.student_trade.repository.StudentRepository;
import za.ac.student_trade.repository.TransactionRepository;
import za.ac.student_trade.service.EmailService;
import za.ac.student_trade.service.ITransactionService;

import java.util.List;

@Service
public class TransactionServiceImpl implements ITransactionService {

    private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;
    private final StudentRepository studentRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  ProductRepository productRepository,
                                  StudentRepository studentRepository) {
        this.transactionRepository = transactionRepository;
        this.productRepository = productRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional
    public Transaction createTransaction(Transaction transaction, Long productSoldId, String buyerId) {
        Product productSold = productRepository.findById(productSoldId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productSoldId));

        Student buyer = studentRepository.findById(buyerId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + buyerId));

        Student seller = productSold.getSeller();

        Transaction newTransaction = new Transaction.Builder()
                .setPrice(productSold.getPrice())
                .setProduct(productSold)
                .setProductLabel(productSold.getProductName())
                .setImageOfProduct(productSold.getImageData())
                .setProductCondition(productSold.getCondition())
                .setProductDescription(productSold.getProductDescription())
                .setBuyer(buyer)
                .setSeller(seller)
                .build();

        Transaction savedTransaction = transactionRepository.save(newTransaction);

        // Send email notifications
        try {
            // Notify seller
            emailService.sendPurchaseNotification(
                    seller.getEmail(),
                    productSold.getProductName(),
                    productSold.getPrice(),
                    buyer.getFirstName() + " " + buyer.getLastName(),
                    buyer.getEmail(),
                    productSold.getImageData(),   // <-- send product image
                    productSold.getImageType()    // <-- e.g. "image/jpeg" or "image/png"
            );

            // Confirm purchase to buyer
            emailService.sendPurchaseConfirmation(
                    buyer.getEmail(),
                    productSold.getProductName(),
                    productSold.getPrice(),
                    seller.getFirstName() + " " + seller.getLastName(),
                    seller.getEmail(),
                    productSold.getImageData(),   // <-- send product image
                    productSold.getImageType()
            );
        } catch (Exception e) {
            System.err.println("Failed to send email notifications: " + e.getMessage());
        }

        return savedTransaction;
    }

    // Other methods...
    @Override
    public Transaction create(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction read(String id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));
    }

    @Override
    public Transaction update(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }
}
