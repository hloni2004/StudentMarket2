package za.ac.student_trade.service.Impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.student_trade.domain.Address;
import za.ac.student_trade.domain.Residence;
import za.ac.student_trade.domain.Student;
import za.ac.student_trade.factory.AddressFactory;
import za.ac.student_trade.factory.ResidenceFactory;
import za.ac.student_trade.factory.StudentFactory;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StudentServiceImplTest {

    @Autowired
    private StudentServiceImpl studentServiceImpl;
    @Autowired
    private AddressServiceImpl addressServiceImpl;
    @Autowired
    private ResidenceServiceImpl residenceServiceImpl;

    private static Residence residence;
    private static Address address;
    private static Student student;

    @BeforeAll
    static void setUp() {
        address = AddressFactory.createAddress("22","Brackeer", "Phola Park", "Piet Retief", "Mpumalanga",2380);

        residence = ResidenceFactory.createResidence("President House", "222", 2,"South Point", address);

        student = StudentFactory.createStudent("Asanda", "Ndhlela", "asandat@gmail.com","asanda123", residence);

    }
    @Test
    void create() {
        Address address1 = addressServiceImpl.create(address);
        assertNotNull(address1);

        //saving residence

        Residence residence1 = residenceServiceImpl.create(residence);
        assertNotNull(residence1);
        System.out.println(residence1.toString());

        //saving student
        Student created = studentServiceImpl.create(student);
        assertNotNull(created);
        System.out.println(created.toString());
    }

    @Test
    @Disabled
    void read() {

    }

    @Test
    @Disabled
    void update() {
    }
}