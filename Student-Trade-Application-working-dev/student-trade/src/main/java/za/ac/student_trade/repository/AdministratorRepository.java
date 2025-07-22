package za.ac.student_trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.ac.student_trade.domain.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
}
