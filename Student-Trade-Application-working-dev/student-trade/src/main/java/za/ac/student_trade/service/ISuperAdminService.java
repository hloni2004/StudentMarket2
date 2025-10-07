package za.ac.student_trade.service;

import za.ac.student_trade.domain.Administrator;
import za.ac.student_trade.domain.SuperAdmin;

import java.util.List;

public interface ISuperAdminService extends IService<SuperAdmin, Long> {
    List<SuperAdmin> findByEmailAndPassword(String email, String password);

    // Admin management methods
    Administrator createAdmin(Administrator administrator);
    Administrator updateAdmin(Administrator administrator);
    void deleteAdmin(Long adminId);
    Administrator getAdminById(Long adminId);
    List<Administrator> getAllAdmins();
}