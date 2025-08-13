package za.ac.student_trade.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.student_trade.domain.Administrator;
import za.ac.student_trade.repository.AdministratorRepository;
import za.ac.student_trade.service.IAdministratorService;

import java.util.List;

@Service
public class AdministratorServiceImpl implements IAdministratorService {


    private AdministratorRepository administratorRepository;

    @Autowired
    public AdministratorServiceImpl(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    @Override
    public Administrator create(Administrator administrator) {
        return this.administratorRepository.save(administrator);
    }

    @Override
    public Administrator read(Long id) {
        return  administratorRepository.findById(id).orElse(null);
    }

    @Override
    public Administrator update(Administrator administrator) {
        return administratorRepository.save(administrator);
    }
    @Override
    public List<Administrator> getAll() {
        return administratorRepository.findAll();
    }

    @Override
    public Administrator findByEmail(String email) {
        return administratorRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Administrator findByUsername(String username) {
        return administratorRepository.findByUsername(username).orElse(null);
    }

    @Override
    public Administrator findByEmailAndPassword(String email, String password) {
        return administratorRepository.findByEmailAndPassword(email, password).orElse(null);
    }

    @Override
    public Administrator findByUsernameAndPassword(String username, String password) {
        return administratorRepository.findByUsernameAndPassword(username, password).orElse(null);
    }
}



