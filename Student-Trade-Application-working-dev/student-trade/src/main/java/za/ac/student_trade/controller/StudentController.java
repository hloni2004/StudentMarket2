package za.ac.student_trade.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.student_trade.domain.Student;
import za.ac.student_trade.service.IService;
import za.ac.student_trade.service.Impl.StudentServiceImpl;

@RestController
@RequestMapping("/api/students")
public class StudentController {


    private StudentServiceImpl service;

    @Autowired
    public void setStudentService(StudentServiceImpl service) {
        this.service = service;
    }

    //<< --CRUD methods start -->>
    @PostMapping("/create")
    public Student create(@RequestBody Student student) {
        return this.service.create(student);
    }

    @GetMapping("/read/{id}")
    public Student read(@PathVariable String id) {
        return this.service.read(id);
    }

    @PutMapping("/update")
    public Student update(@RequestBody Student student) {
        return this.service.update(student);
    }




}
