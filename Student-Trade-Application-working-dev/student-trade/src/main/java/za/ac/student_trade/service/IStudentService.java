package za.ac.student_trade.service;

import za.ac.student_trade.domain.Student;

import java.util.List;

public interface IStudentService extends IService<Student , Long> {
    List<Student> findByEmail(String email);
}
