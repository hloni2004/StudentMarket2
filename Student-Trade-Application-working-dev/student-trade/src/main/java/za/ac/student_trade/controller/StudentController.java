package za.ac.student_trade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.ac.student_trade.domain.Administrator;
import za.ac.student_trade.domain.Student;
import za.ac.student_trade.domain.SuperAdmin;
import za.ac.student_trade.service.Impl.AdministratorServiceImpl;
import za.ac.student_trade.service.Impl.StudentServiceImpl;
import za.ac.student_trade.service.Impl.SuperAdminServiceImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("api/student")
public class StudentController {

    private StudentServiceImpl studentService;
    private AdministratorServiceImpl administratorServiceImpl;
    private SuperAdminServiceImpl superAdminServiceImpl;

    @Autowired
    public void setStudentService(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @Autowired
    public void setAdministratorServiceImpl(AdministratorServiceImpl administratorServiceImpl) {
        this.administratorServiceImpl = administratorServiceImpl;
    }

    @Autowired
    public void setSuperAdminServiceImpl(SuperAdminServiceImpl superAdminServiceImpl) {
        this.superAdminServiceImpl = superAdminServiceImpl;
    }

    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.create(student));
    }


    @GetMapping("/read/{id}")
    public Student read(@PathVariable String id) {
        return this.studentService.read(id);
    }

    @PutMapping("/update")
    public Student update(@RequestBody Student student) {
        return this.studentService.update(student);
    }


    @PatchMapping(value = "/update/{id}", consumes = { "multipart/form-data" })
    public ResponseEntity<Student> updateStudent(
            @PathVariable("id") String studentId,
            @RequestPart("student") Student studentRequest,
            @RequestPart(value = "profileImage", required = false) MultipartFile profileImage
    ) throws IOException {
        Student updated = studentService.updateStudent(studentId, studentRequest, profileImage);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/getAll")
    public List<Student> getAll() {
        return this.studentService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        studentService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        try {
            // Priority 1: Check SuperAdmin first (highest authority)
            List<SuperAdmin> superAdmins = superAdminServiceImpl.findByEmailAndPassword(email.trim(), password.trim());
            if (!superAdmins.isEmpty()) {
                SuperAdmin superAdmin = superAdmins.get(0);

                Map<String, Object> successResponse = new HashMap<>();
                successResponse.put("success", true);
                successResponse.put("message", "Super Admin login successful");
                successResponse.put("role", "superadmin");

                Map<String, Object> superAdminData = new HashMap<>();
                superAdminData.put("superAdminId", superAdmin.getSuperAdminId());
                superAdminData.put("username", superAdmin.getUsername());
                superAdminData.put("email", superAdmin.getEmail());
                successResponse.put("data", superAdminData);

                return ResponseEntity.ok(successResponse);
            }

            // Priority 2: Check Admin
            List<Administrator> admins = administratorServiceImpl.findByEmailAndPassword(email.trim(), password.trim());
            if (!admins.isEmpty()) {
                Administrator admin = admins.get(0);
                Map<String, Object> successResponse = new HashMap<>();
                successResponse.put("success", true);
                successResponse.put("message", "Admin login successful");
                successResponse.put("role", "admin");

                Map<String, Object> adminData = new HashMap<>();
                adminData.put("adminId", admin.getAdminId());
                adminData.put("firstName", admin.getUsername());
                adminData.put("email", admin.getEmail());
                successResponse.put("data", adminData);

                return ResponseEntity.ok(successResponse);
            }

            // Priority 3: Check Student
            List<Student> students = studentService.findByEmailAndPassword(email.trim(), password.trim());
            if (!students.isEmpty()) {
                Student student = students.get(0);

                Map<String, Object> successResponse = new HashMap<>();
                successResponse.put("success", true);
                successResponse.put("message", "Student login successful");
                successResponse.put("role", "student");

                Map<String, Object> studentData = new HashMap<>();
                studentData.put("studentId", student.getStudentId());
                studentData.put("firstName", student.getFirstName());
                studentData.put("lastName", student.getLastName());
                studentData.put("email", student.getEmail());
                successResponse.put("data", studentData);

                return ResponseEntity.ok(successResponse);
            }

            // None found
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Internal server error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}