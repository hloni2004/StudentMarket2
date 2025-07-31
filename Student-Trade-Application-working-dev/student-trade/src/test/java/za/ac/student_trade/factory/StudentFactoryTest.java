package za.ac.student_trade.factory;

import org.junit.jupiter.api.Test;
import za.ac.student_trade.domain.Address;
import za.ac.student_trade.domain.Residence;
import za.ac.student_trade.domain.Student;

import static org.junit.jupiter.api.Assertions.*;

class StudentFactoryTest {

    static Address address = AddressFactory.createAddress(
            "45",
            "Dorset Street",
            "Claremont",
            "Cape Town",
            "Western Cape",
            7708
    );
    static Residence residence = ResidenceFactory.createResidence(
            "Varsity Lodge",
            "A101",
            1,
            "Block A",
            address
    );
    private static Student studentThree = StudentFactory.createStudent("Brian", "O'Connor","brianoconnor@gmail.com", "2fast2farious", residence);

    @Test
    void createStudent() {
        assertNotNull(studentThree);
        System.out.println(studentThree.toString());
    }
}