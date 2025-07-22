package za.ac.student_trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.student_trade.domain.Residence;

public interface ResidenceRepository extends JpaRepository<Residence, Long> {
}
