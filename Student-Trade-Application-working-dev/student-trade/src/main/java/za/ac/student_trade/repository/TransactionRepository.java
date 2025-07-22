package za.ac.student_trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.student_trade.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
