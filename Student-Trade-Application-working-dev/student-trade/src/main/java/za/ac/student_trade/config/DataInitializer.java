package za.ac.student_trade.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.student_trade.domain.SuperAdmin;
import za.ac.student_trade.factory.SuperAdminFactory;
import za.ac.student_trade.repository.SuperAdminRepository;

@Component
public class DataInitializer {

    private final SuperAdminRepository superAdminRepository;

    @Autowired
    public DataInitializer(SuperAdminRepository superAdminRepository) {
        this.superAdminRepository = superAdminRepository;
    }

    @PostConstruct
    public void init() {
        createDefaultSuperAdmin();
    }

    private void createDefaultSuperAdmin() {
        // Check if a super admin already exists
        if (superAdminRepository.count() == 0) {
            // Create default super admin
            SuperAdmin superAdmin = SuperAdminFactory.createSuperAdmin(
                    "superadmin",
                    "superadmin@studenttrade.ac.za",
                    "SuperAdmin@123"
            );

            if (superAdmin != null) {
                superAdminRepository.save(superAdmin);
                System.out.println("========================================");
                System.out.println(" DEFAULT SUPER ADMIN CREATED");
                System.out.println("========================================");
                System.out.println("Username: superadmin");
                System.out.println("Email: superadmin@studenttrade.ac.za");
                System.out.println("Password: SuperAdmin@123");
                System.out.println("========================================");
                System.out.println("  PLEASE CHANGE THE PASSWORD AFTER FIRST LOGIN");
                System.out.println("========================================");
            } else {
                System.err.println(" Failed to create default super admin");
            }
        } else {
            System.out.println("ℹ  Super Admin already exists. Skipping creation.");
        }
    }
}