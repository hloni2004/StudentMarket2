package za.ac.student_trade.service.Impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.student_trade.domain.Residence;
import za.ac.student_trade.factory.ResidenceFactory;
import za.ac.student_trade.repository.ResidenceRepository;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ResidenceServiceImplTest {

    @Autowired
    private ResidenceServiceImpl service;
    private static Residence residence;

    @BeforeEach
    void setUp() {
        residence = ResidenceFactory.createResidence(null, "New Market Junction", "367", 3, "C Block");
    }
    @Test
    void create() {
        Residence created = service.create(residence);
        assertNotNull(created);
        System.out.println("Created: " + created);
    }


@Test
void read() {
    Residence created = service.create(residence);
    Residence read = service.read(created.getResidenceId());
    assertNotNull(read);
    System.out.println(read);
}


@Test
void update() {
        service.create(residence);
        Residence updated = new Residence.Builder().copy(residence).setBuildingName("Block B").setFloorNumber(2).setRoomNumber("245").build();

        Residence result = service.update(updated);
        assertEquals("Block B", result.getBuildingName());
        System.out.println("Updated: " + result);
    }


@Test
void getAll() {
        System.out.println(service.getAll());
}
}