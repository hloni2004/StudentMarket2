package za.ac.student_trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.ac.student_trade.domain.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
    Optional<Administrator> findByEmail(String email);
    Optional<Administrator> findByUsernam(String username);

}
