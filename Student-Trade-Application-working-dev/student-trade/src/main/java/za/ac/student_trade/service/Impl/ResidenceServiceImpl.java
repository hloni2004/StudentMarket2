package za.ac.student_trade.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.student_trade.domain.Residence;
import za.ac.student_trade.repository.ResidenceRepository;
import za.ac.student_trade.service.ResidenceService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResidenceServiceImpl implements ResidenceService <Residence, Long> {

    private ResidenceRepository residenceRepository;

    @Autowired
    public ResidenceServiceImpl(ResidenceRepository residenceRepository) {
        this.residenceRepository = residenceRepository;
    }

    @Override
    public Residence create(Residence residence) {
        return residenceRepository.save(residence);
    }

    @Override
    public Residence read(Long id) {
        return this.residenceRepository.findById(id).orElse(null);
    }

    @Override
    public Residence update(Residence residence) {
        return residenceRepository.save(residence);
    }

    @Override
    public List<Residence> getAll() {
        return residenceRepository.findAll();
    }
}