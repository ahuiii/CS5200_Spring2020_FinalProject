package edu.northeastern.cs5200.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.northeastern.cs5200.entities.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long>{
	
	Project findByProjectIdentifier(String projectId);
	Project findByProjectNameLike(String projectName);
	//Project findByProjectId(String projectId);
	@Override
    Iterable<Project> findAll();
}
