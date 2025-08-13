package za.ac.student_trade.factory;

import org.junit.jupiter.api.Test;
import za.ac.student_trade.domain.Administrator;

import static org.junit.jupiter.api.Assertions.*;
class AdministratorFactoryTest {

    Administrator admin = AdministratorFactory.createAdministrator(
            "adminUser", "admin@example.com", "securePass123");

    @Test
    void createAdministrator() {
        System.out.println(admin);
        assertNotNull(admin);
        System.out.println(admin);
    }
}