package edu.northeastern.cs5200.services;

import edu.northeastern.cs5200.entities.Engineer;
import edu.northeastern.cs5200.entities.Project;
import edu.northeastern.cs5200.repository.EngineerRepository;
import org.springframework.stereotype.Service;

@Service
public class EngineerService {

    private final EngineerRepository engineerRepository;

    public EngineerService(EngineerRepository engineerRepository){
        this.engineerRepository = engineerRepository;
    }
    public Iterable<Engineer> getAllEngineer(){
        return engineerRepository.findAll();
    }

    public Engineer findEngineerById(int engineerId){
        Engineer engineer = engineerRepository.findById(engineerId);
        return engineer;
    }
    public void deleteEngineerById(int engineerId){
        Engineer engineer = findEngineerById(engineerId);
        engineerRepository.delete(engineer);
    }
}
