package za.ac.student_trade.service;

import za.ac.student_trade.domain.Product;
import za.ac.student_trade.domain.Student;
import za.ac.student_trade.domain.Transaction;

public interface ITransactionService extends IService <Transaction, String> {

    Transaction createTransaction(Transaction transaction, Long productSoldId, String buyerId);
}
