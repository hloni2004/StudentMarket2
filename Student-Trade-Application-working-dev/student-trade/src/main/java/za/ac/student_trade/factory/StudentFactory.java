package za.ac.student_trade.factory;

import za.ac.student_trade.domain.Residence;
import za.ac.student_trade.domain.Student;
import za.ac.student_trade.util.Helper;

public class StudentFactory {

    public static Student createStudent(String firstName, String lastName, String email, String password, Residence residence) {

        String studentId = Helper.generateId();
        return new Student.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .setResidence(residence)
                .build();
    }
}
