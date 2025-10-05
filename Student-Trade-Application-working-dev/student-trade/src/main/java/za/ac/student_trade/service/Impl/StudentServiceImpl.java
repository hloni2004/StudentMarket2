package za.ac.student_trade.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import za.ac.student_trade.domain.Residence;
import za.ac.student_trade.domain.Student;
import za.ac.student_trade.factory.StudentFactory;
import za.ac.student_trade.repository.ResidenceRepository;
import za.ac.student_trade.repository.StudentRepository;
import za.ac.student_trade.service.IStudentService;

import java.io.IOException;
import java.util.*;

@Service
public class StudentServiceImpl implements IStudentService{

    private final StudentRepository studentRepository;
private final ResidenceRepository residenceRepository;


    public StudentServiceImpl(StudentRepository studentRepository, ResidenceRepository residenceRepository) {
        this.studentRepository = studentRepository;
        this.residenceRepository = residenceRepository;
    }

    @Override
    public Student create(Student student) {

        Student createdStudent = StudentFactory.createStudent(
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getPassword(),
                student.getResidence()
        );

        return studentRepository.save(createdStudent);
    }


    @Override
    public Student read(String s) {
        return this.studentRepository.findById(s).orElse(null);
    }


    @Override
    public Student update(Student student) {
        return this.studentRepository.save(student);
    }




    @Override
    public List<Student> getAll(){
        return this.studentRepository.findAll();
    }

    @Override
    public void delete(String studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public List<Student> findByEmailAndPassword(String email, String password) {
        System.out.println("Searching for email: '" + email + "' and password: '" + password + "'");

        List<Student> result = this.studentRepository.findByEmailAndPassword(email, password);
        System.out.println("Found " + result.size() + " students");

        return result;
    }

    @Override
    public Student updateStudent(String studentId, Student request, MultipartFile profileImage) throws IOException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));

        Student.Builder studentBuilder = new Student.Builder().copy(student);

        // --- Update fields if present ---
        if (request.getFirstName() != null) studentBuilder.setFirstName(request.getFirstName());
        if (request.getLastName() != null) studentBuilder.setLastName(request.getLastName());
        if (request.getEmail() != null) studentBuilder.setEmail(request.getEmail());

        // --- Update residence ---
        if (request.getResidence() != null) {
            Residence currentResidence = student.getResidence();
            Residence.Builder residenceBuilder = (currentResidence != null)
                    ? new Residence.Builder().copy(currentResidence)
                    : new Residence.Builder();

            residenceBuilder
                    .setResidenceName(request.getResidence().getResidenceName())
                    .setRoomNumber(request.getResidence().getRoomNumber())
                    .setFloorNumber(request.getResidence().getFloorNumber())
                    .setBuildingName(request.getResidence().getBuildingName())
                    .setAddress(request.getResidence().getAddress());

            Residence updatedResidence = residenceBuilder.build();
            residenceRepository.save(updatedResidence);
            studentBuilder.setResidence(updatedResidence);
        }

        // --- Optional profile image ---
        if (profileImage != null && !profileImage.isEmpty()) {
            studentBuilder.setProfileImage(profileImage.getBytes());
        }

        Student updatedStudent = studentBuilder.build();
        return studentRepository.save(updatedStudent);
    }

}

