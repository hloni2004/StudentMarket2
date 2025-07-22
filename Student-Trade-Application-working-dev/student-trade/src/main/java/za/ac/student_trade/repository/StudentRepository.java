package za.ac.student_trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.student_trade.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
