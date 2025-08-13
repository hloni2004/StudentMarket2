package za.ac.student_trade.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.student_trade.domain.Administrator;
import za.ac.student_trade.domain.Student;
import za.ac.student_trade.service.IAdministratorService;
import za.ac.student_trade.service.IStudentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final IStudentService studentService;
    private final IAdministratorService administratorService;

    public AuthController(IStudentService studentService, IAdministratorService administratorService) {
        this.studentService = studentService;
        this.administratorService = administratorService;
    }

    public static class LoginRequest {
        public String email;
        public String password;

        public String getEmail() { return email; }
        public String getPassword() { return password; }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // Try student lookup by email
        List<Student> students = studentService.findByEmail(request.getEmail());
        if (students != null && !students.isEmpty()) {
            Student student = students.get(0);
            if (student.getPassword().equals(request.getPassword())) {
                Map<String, Object> body = new HashMap<>();
                body.put("role", "STUDENT");
                body.put("userId", student.getStudentId());
                body.put("redirect", "/student/dashboard");
                return ResponseEntity.ok(body);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        }

        // Try admin lookup by email
        return administratorService.findByEmail(request.getEmail())
                .map(admin -> {
                    if (admin.getPassword().equals(request.getPassword())) {
                        Map<String, Object> body = new HashMap<>();
                        body.put("role", "ADMIN");
                        body.put("userId", admin.getAdminId());
                        body.put("redirect", "/admin/dashboard");
                        return ResponseEntity.ok(body);
                    } else {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
                    }
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found"));
    }
}