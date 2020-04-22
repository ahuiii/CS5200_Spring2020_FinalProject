package edu.northeastern.cs5200.repository;

import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5200.entities.Leader;

public interface LeaderRepository extends CrudRepository<Leader, Integer>{

}
