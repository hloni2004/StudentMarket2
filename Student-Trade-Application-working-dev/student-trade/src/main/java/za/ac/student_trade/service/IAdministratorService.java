package za.ac.student_trade.service;

import za.ac.student_trade.domain.Administrator;

import java.util.List;

public interface IAdministratorService extends IService <Administrator, Long>{
   // List<Administrator> getAll();
   Administrator findByEmail(String email);
   Administrator findByUsername(String username);
   Administrator findByEmailAndPassword(String email, String password);
   Administrator findByUsernameAndPassword(String username, String password);
}
