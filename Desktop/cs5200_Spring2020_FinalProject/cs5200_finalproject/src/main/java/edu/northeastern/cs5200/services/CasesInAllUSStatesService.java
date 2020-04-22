package edu.northeastern.cs5200.services;

import edu.northeastern.cs5200.entities.CasesInAllUSStates;
import edu.northeastern.cs5200.repository.CasesInAllUSStatesRepository;
import edu.northeastern.cs5200.web.feign.dto.CasesInAllUSStatesDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CasesInAllUSStatesService {

  private final CasesInAllUSStatesRepository repository;

  public CasesInAllUSStatesService(
      CasesInAllUSStatesRepository repository) {
    this.repository = repository;
  }


  public List<CasesInAllUSStates> getAllCases() {
    return (List<CasesInAllUSStates>) repository.findAll();
  }

  @Transactional
  public List<CasesInAllUSStates> saveAll(List<CasesInAllUSStatesDto> cases) {
    List<CasesInAllUSStates> updatedCases = cases.stream()
        .map(c -> {
          CasesInAllUSStates casesInAllUSStates = repository
              .findCasesInAllUSStatesByUSAState(c.USAState());
          if (casesInAllUSStates == null) {
            casesInAllUSStates = new CasesInAllUSStates();
          }
          casesInAllUSStates.setUSAState(c.USAState());
          casesInAllUSStates.setTotalCases(c.TotalCases());
          casesInAllUSStates.setNewCases(c.NewCases());
          casesInAllUSStates.setTotalDeaths(c.TotalDeaths());
          casesInAllUSStates.setNewDeaths(c.NewDeaths());
          casesInAllUSStates.setActiveCases(c.ActiveCases());
          casesInAllUSStates.setTotalTests(c.TotalTests());
          casesInAllUSStates.setTot_Cases_1M_Pop(c.Tot_Cases_1M_Pop());
          casesInAllUSStates.setDeaths_1M_Pop(c.Deaths_1M_Pop());
          casesInAllUSStates.setTests_1M_Pop(c.Tests_1M_Pop());
          return casesInAllUSStates;
        })
        .collect(Collectors.toList());
    return (List<CasesInAllUSStates>) repository.saveAll(updatedCases);
  }

}
