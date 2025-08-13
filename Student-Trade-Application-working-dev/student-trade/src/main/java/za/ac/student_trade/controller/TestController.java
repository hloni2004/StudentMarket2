package za.ac.student_trade.controller;

import org.springframework.web.bind.annotation.*;
import za.ac.student_trade.dto.LoginRequest;
import za.ac.student_trade.dto.LoginResponse;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*")
public class TestController {

    @GetMapping("/login-system")
    public String testLoginSystem() {
        return "Login system is working!";
    }

    @PostMapping("/mock-login")
    public LoginResponse mockLogin(@RequestBody LoginRequest loginRequest) {
        // Mock login for testing purposes
        if ("student@test.com".equals(loginRequest.getEmailOrUsername()) && 
            "password123".equals(loginRequest.getPassword())) {
            return new LoginResponse(
                true,
                "Student login successful (MOCK)",
                "STUDENT",
                "/student-dashboard.html",
                "ST123456",
                "Test Student"
            );
        } else if ("admin".equals(loginRequest.getEmailOrUsername()) && 
                   "admin123".equals(loginRequest.getPassword())) {
            return new LoginResponse(
                true,
                "Admin login successful (MOCK)",
                "ADMIN",
                "/admin-dashboard.html",
                "1",
                "Test Admin"
            );
        } else {
            return new LoginResponse(
                false,
                "Invalid credentials (MOCK)",
                null,
                null,
                null,
                null
            );
        }
    }
}