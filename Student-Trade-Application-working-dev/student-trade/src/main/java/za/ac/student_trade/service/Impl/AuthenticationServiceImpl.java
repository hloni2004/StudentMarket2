package za.ac.student_trade.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.student_trade.domain.Administrator;
import za.ac.student_trade.domain.Student;
import za.ac.student_trade.dto.LoginRequest;
import za.ac.student_trade.dto.LoginResponse;
import za.ac.student_trade.repository.AdministratorRepository;
import za.ac.student_trade.repository.StudentRepository;
import za.ac.student_trade.service.AuthenticationService;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final StudentRepository studentRepository;
    private final AdministratorRepository administratorRepository;

    @Autowired
    public AuthenticationServiceImpl(StudentRepository studentRepository, 
                                   AdministratorRepository administratorRepository) {
        this.studentRepository = studentRepository;
        this.administratorRepository = administratorRepository;
    }

    @Override
    public LoginResponse authenticate(LoginRequest loginRequest) {
        String emailOrUsername = loginRequest.getEmailOrUsername();
        String password = loginRequest.getPassword();

        // First, try to authenticate as a student
        Optional<Student> student = studentRepository.findByEmailAndPassword(emailOrUsername, password);
        if (student.isPresent()) {
            Student foundStudent = student.get();
            return new LoginResponse(
                true,
                "Student login successful",
                "STUDENT",
                "/student/dashboard", // Redirect to student dashboard
                foundStudent.getStudentId(),
                foundStudent.getFirstName() + " " + foundStudent.getLastName()
            );
        }

        // If not a student, try to authenticate as an admin
        // First try with email
        Optional<Administrator> adminByEmail = administratorRepository.findByEmailAndPassword(emailOrUsername, password);
        if (adminByEmail.isPresent()) {
            Administrator foundAdmin = adminByEmail.get();
            return new LoginResponse(
                true,
                "Admin login successful",
                "ADMIN",
                "/admin/dashboard", // Redirect to admin dashboard
                foundAdmin.getAdminId().toString(),
                foundAdmin.getUsername()
            );
        }

        // Try with username
        Optional<Administrator> adminByUsername = administratorRepository.findByUsernameAndPassword(emailOrUsername, password);
        if (adminByUsername.isPresent()) {
            Administrator foundAdmin = adminByUsername.get();
            return new LoginResponse(
                true,
                "Admin login successful",
                "ADMIN",
                "/admin/dashboard", // Redirect to admin dashboard
                foundAdmin.getAdminId().toString(),
                foundAdmin.getUsername()
            );
        }

        // If neither student nor admin found, return failure response
        return new LoginResponse(
            false,
            "Invalid credentials. Please check your email/username and password.",
            null,
            null,
            null,
            null
        );
    }
}