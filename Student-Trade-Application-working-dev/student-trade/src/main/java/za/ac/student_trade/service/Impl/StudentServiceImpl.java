package za.ac.student_trade.service.Impl;

import org.springframework.stereotype.Service;
import za.ac.student_trade.domain.Student;
import za.ac.student_trade.repository.StudentRepository;
import za.ac.student_trade.service.IService;
@Service
public class StudentServiceImpl implements IService<Student, String> {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student read(String id) {
        return this.studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student update(Student student) {
        return this.studentRepository.save(student);
    }
}
