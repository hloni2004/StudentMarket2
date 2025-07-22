package za.ac.student_trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.student_trade.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
