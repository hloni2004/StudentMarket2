package za.ac.student_trade.service.Impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.student_trade.domain.Address;
import za.ac.student_trade.factory.AddressFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddressServiceImplTest {
    @Autowired
    private AddressServiceImpl service;

    private  static Address address;

    @BeforeAll
    static void setup() {
        address = AddressFactory.createAddress(
                "123", "Main St", "Central", "Durban", "Western Cape", 8000
        );
    }
    @Test
    @Order(1)
    void create() {
        assertNotNull(address);
        address = service.create(address);
        System.out.println(address);
    }

    @Test
    @Order(2)
    void read() {
        Address read = service.read(address.getAddressId());
        System.out.println(read);
    }

    @Test
    @Order(3)
    void update() {
        Address updatedAddress = new Address.Builder().copy(address).setCity("Cape Town").build();
        address = service.update(updatedAddress);
        System.out.println("address = " + address);
    }

    @Test
    @Order(4)
    void getAll() {
        List<Address> all = service.getAll();
    }

}