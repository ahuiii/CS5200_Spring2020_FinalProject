package edu.northeastern.cs5200.repository;

import edu.northeastern.cs5200.entities.CasesInAllUSStates;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CasesInAllUSStatesRepository extends CrudRepository<CasesInAllUSStates, Integer> {

  CasesInAllUSStates findCasesInAllUSStatesByUSAState(String state);
}
