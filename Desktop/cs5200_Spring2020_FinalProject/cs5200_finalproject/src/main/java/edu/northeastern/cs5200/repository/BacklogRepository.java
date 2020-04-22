package edu.northeastern.cs5200.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import edu.northeastern.cs5200.entities.Backlog;
@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long> {
	Backlog findByProjectIdentifier(String Identifier);
	
}
