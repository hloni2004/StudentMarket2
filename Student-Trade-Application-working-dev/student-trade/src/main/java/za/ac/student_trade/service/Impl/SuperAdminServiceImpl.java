package za.ac.student_trade.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.student_trade.domain.Administrator;
import za.ac.student_trade.domain.SuperAdmin;
import za.ac.student_trade.repository.AdministratorRepository;
import za.ac.student_trade.repository.SuperAdminRepository;
import za.ac.student_trade.service.ISuperAdminService;

import java.util.List;

@Service
public class SuperAdminServiceImpl implements ISuperAdminService {

    private final SuperAdminRepository superAdminRepository;
    private final AdministratorRepository administratorRepository;

    @Autowired
    public SuperAdminServiceImpl(SuperAdminRepository superAdminRepository,
                                 AdministratorRepository administratorRepository) {
        this.superAdminRepository = superAdminRepository;
        this.administratorRepository = administratorRepository;
    }

    @Override
    public SuperAdmin create(SuperAdmin superAdmin) {
        return superAdminRepository.save(superAdmin);
    }

    @Override
    public SuperAdmin read(Long id) {
        return superAdminRepository.findById(id).orElse(null);
    }

    @Override
    public SuperAdmin update(SuperAdmin superAdmin) {
        return superAdminRepository.save(superAdmin);
    }

    @Override
    public List<SuperAdmin> getAll() {
        return superAdminRepository.findAll();
    }

    @Override
    public List<SuperAdmin> findByEmailAndPassword(String email, String password) {
        System.out.println("Searching for super admin with email: '" + email + "' and password: '" + password + "'");
        List<SuperAdmin> result = superAdminRepository.findByEmailAndPassword(email, password);
        System.out.println("Found " + result.size() + " super admin(s)");
        return result;
    }

    // Admin management methods
    @Override
    public Administrator createAdmin(Administrator administrator) {
        return administratorRepository.save(administrator);
    }

    @Override
    public Administrator updateAdmin(Administrator administrator) {
        if (!administratorRepository.existsById(administrator.getAdminId())) {
            throw new RuntimeException("Administrator not found with id: " + administrator.getAdminId());
        }
        return administratorRepository.save(administrator);
    }

    @Override
    public void deleteAdmin(Long adminId) {
        if (!administratorRepository.existsById(adminId)) {
            throw new RuntimeException("Administrator not found with id: " + adminId);
        }
        administratorRepository.deleteById(adminId);
    }

    @Override
    public Administrator getAdminById(Long adminId) {
        return administratorRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Administrator not found with id: " + adminId));
    }

    @Override
    public List<Administrator> getAllAdmins() {
        return administratorRepository.findAll();
    }
}