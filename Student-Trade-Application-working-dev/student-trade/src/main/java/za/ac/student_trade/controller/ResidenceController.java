package za.ac.student_trade.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.student_trade.domain.Residence;
import za.ac.student_trade.service.Impl.ResidenceServiceImpl;
import za.ac.student_trade.service.ResidenceService;

import java.util.List;

@RestController
@RequestMapping("/residence")
public class ResidenceController {

    private ResidenceServiceImpl service;

    @Autowired
    public ResidenceController(ResidenceServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Residence createResidence(@RequestBody Residence residence) {
        return service.create(residence);
    }

    @GetMapping("/read/{residenceId}")
    public Residence readResidence(@PathVariable Long residenceId) {
        return service.read(residenceId);
    }

    @PutMapping("/update")
    public Residence updateResidence(@RequestBody Residence residence) {
        return service.update(residence);

    }

    @GetMapping("/getAll")
    public List<Residence> getAllResidence() {
        return service.getAll();
    }


}
