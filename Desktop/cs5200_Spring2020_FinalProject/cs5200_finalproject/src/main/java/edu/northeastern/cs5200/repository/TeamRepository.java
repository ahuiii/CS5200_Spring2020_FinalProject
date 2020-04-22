package edu.northeastern.cs5200.repository;


import edu.northeastern.cs5200.entities.Engineer;
import edu.northeastern.cs5200.entities.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TeamRepository extends CrudRepository<Team, Integer> {

  Team findById(int id);

  Team findByProjectId(int id);


  Iterable<Engineer> findByEngineerInTeam(int id);

}
