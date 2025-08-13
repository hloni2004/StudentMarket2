package za.ac.student_trade.service;

import za.ac.student_trade.domain.Student;

import java.util.List;

public interface IStudentService extends IService<Student , String> {
    List<Student> findByEmail(String email);
    Student findByEmailAndPassword(String email, String password);
}
