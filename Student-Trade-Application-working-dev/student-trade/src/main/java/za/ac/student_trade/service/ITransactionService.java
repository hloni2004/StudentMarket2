package za.ac.student_trade.service;

import org.springframework.web.multipart.MultipartFile;
import za.ac.student_trade.domain.Product;
import za.ac.student_trade.domain.Student;
import za.ac.student_trade.domain.Transaction;

import java.io.IOException;

public interface ITransactionService extends IService <Transaction, String> {

    Transaction createTransaction(Transaction transaction, Long productSoldId, String buyerId);
}
