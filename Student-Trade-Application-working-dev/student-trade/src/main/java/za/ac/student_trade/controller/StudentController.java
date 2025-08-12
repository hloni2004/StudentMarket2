package za.ac.student_trade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.student_trade.domain.Student;
import za.ac.student_trade.service.Impl.StudentServiceImpl;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/student")
public class StudentController {

    private StudentServiceImpl studentService;

    @Autowired
    public void setStudentService(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student) {
        return this.studentService.create(student);
    }

    @GetMapping("/read/{id}")
    public Student readStudent(@PathVariable Long id) {
        return this.studentService.read(id);
    }

    @PutMapping("/update")
    public Student updateStudent(@RequestBody Student student) {
        return this.studentService.update(student);
    }

    @GetMapping("/getAll")
    public List<Student> getAll() {
        return this.studentService.getAll();
    }
}