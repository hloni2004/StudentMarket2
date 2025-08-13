package za.ac.student_trade.service;

import za.ac.student_trade.domain.Administrator;

import java.util.List;
import java.util.Optional;

public interface IAdministratorService extends IService <Administrator, Long>{
    Optional<Administrator> findByEmail(String email);
}
