package za.ac.student_trade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.student_trade.domain.Administrator;
import za.ac.student_trade.service.IAdministratorService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin")
public class AdministratorController {

    private IAdministratorService administratorService;

    @Autowired
    public void setAdministratorService(IAdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @PostMapping("/create")
    public Administrator create(@RequestBody Administrator administrator) {
        return administratorService.create(administrator);
    }

    @GetMapping("/read/{id}")
    public Administrator read(@PathVariable Long id) {
        return administratorService.read(id);
    }

    @PutMapping("/update")
    public Administrator update(@RequestBody Administrator administrator) {
        return administratorService.update(administrator);
    }

    @GetMapping("/getAll")
    public List<Administrator> getAll(){
        return administratorService.getAll();
    }
}