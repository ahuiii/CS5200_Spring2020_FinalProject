package edu.northeastern.cs5200.repository;

import edu.northeastern.cs5200.entities.Engineer;
import edu.northeastern.cs5200.entities.Project;
import org.springframework.data.repository.CrudRepository;

public interface EngineerRepository extends CrudRepository<Engineer, Integer> {
    @Override
    Iterable<Engineer> findAll();

    Engineer findById(int id);
}
