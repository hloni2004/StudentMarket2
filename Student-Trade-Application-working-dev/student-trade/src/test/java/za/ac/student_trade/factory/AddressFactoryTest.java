package za.ac.student_trade.factory;

import org.junit.jupiter.api.Test;
import za.ac.student_trade.domain.Address;

import static org.junit.jupiter.api.Assertions.*;

class AddressFactoryTest {

    @Test
    void createAddress() {
        Address address = AddressFactory.createAddress(
                "123", "Main Street", "Central", "Cape Town", "Western Cape", 8000);
        assertNotNull(address);
        System.out.println(address);

    }
}