package edu.northeastern.cs5200.web;

import edu.northeastern.cs5200.entities.CasesInAllUSStates;
import edu.northeastern.cs5200.services.CasesInAllUSStatesService;
import edu.northeastern.cs5200.web.feign.COVID19Client;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/covid19")
@CrossOrigin
public class COVID19Controller {

  private final COVID19Client client;
  private final CasesInAllUSStatesService statesService;

  public COVID19Controller(COVID19Client client,
      CasesInAllUSStatesService statesService) {
    this.client = client;
    this.statesService = statesService;
  }

  @GetMapping(value = "/update", produces = "application/json")
  public ResponseEntity<List<CasesInAllUSStates>> updateCOVID19Data() {
    try {
      return ResponseEntity
          .ok(statesService.saveAll(client.getAllCasesInAmerica().data().get(0).table()));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping(value = "/", produces = "application/json")
  public ResponseEntity<List<CasesInAllUSStates>> getCOVID19Data() {
    try {
      return ResponseEntity.ok(statesService.getAllCases());
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

}
