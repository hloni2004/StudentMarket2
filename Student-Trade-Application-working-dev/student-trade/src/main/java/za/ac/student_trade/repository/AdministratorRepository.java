package za.ac.student_trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.student_trade.domain.Administrator;

import java.util.Optional;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
    Optional<Administrator> findByEmail(String email);
    Optional<Administrator> findByUsername(String username);
    Optional<Administrator> findByEmailAndPassword(String email, String password);
    Optional<Administrator> findByUsernameAndPassword(String username, String password);
}
