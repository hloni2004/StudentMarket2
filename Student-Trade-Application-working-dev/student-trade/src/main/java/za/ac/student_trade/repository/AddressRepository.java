package za.ac.student_trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.student_trade.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
