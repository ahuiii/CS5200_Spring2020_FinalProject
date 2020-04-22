package edu.northeastern.cs5200.entities;

import javax.persistence.*;

@Entity
public class CasesInAllUSStates {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String USAState;

  private String TotalCases;

  private String NewCases;

  private String TotalDeaths;

  private String NewDeaths;

  private String ActiveCases;

  private String TotalTests;

  private String Tot_Cases_1M_Pop;

  private String Deaths_1M_Pop;

  private String Tests_1M_Pop;

//  @ManyToOne()
//  private Team casesInTeam;

//  public Team getCasesInTeam() {
//    return casesInTeam;
//  }
//
//  public void setCasesInTeam(Team casesInTeam) {
//    this.casesInTeam = casesInTeam;
//  }

  public Integer getId() {
    return id;
  }

  public String getUSAState() {
    return USAState;
  }

  public void setUSAState(String USAState) {
    this.USAState = USAState;
  }

  public String getTotalCases() {
    return TotalCases;
  }

  public void setTotalCases(String totalCases) {
    TotalCases = totalCases;
  }

  public String getNewCases() {
    return NewCases;
  }

  public void setNewCases(String newCases) {
    NewCases = newCases;
  }

  public String getTotalDeaths() {
    return TotalDeaths;
  }

  public void setTotalDeaths(String totalDeaths) {
    TotalDeaths = totalDeaths;
  }

  public String getNewDeaths() {
    return NewDeaths;
  }

  public void setNewDeaths(String newDeaths) {
    NewDeaths = newDeaths;
  }

  public String getActiveCases() {
    return ActiveCases;
  }

  public void setActiveCases(String activeCases) {
    ActiveCases = activeCases;
  }

  public String getTotalTests() {
    return TotalTests;
  }

  public void setTotalTests(String totalTests) {
    TotalTests = totalTests;
  }

  public String getTot_Cases_1M_Pop() {
    return Tot_Cases_1M_Pop;
  }

  public void setTot_Cases_1M_Pop(String tot_Cases_1M_Pop) {
    Tot_Cases_1M_Pop = tot_Cases_1M_Pop;
  }

  public String getDeaths_1M_Pop() {
    return Deaths_1M_Pop;
  }

  public void setDeaths_1M_Pop(String deaths_1M_Pop) {
    Deaths_1M_Pop = deaths_1M_Pop;
  }

  public String getTests_1M_Pop() {
    return Tests_1M_Pop;
  }

  public void setTests_1M_Pop(String tests_1M_Pop) {
    Tests_1M_Pop = tests_1M_Pop;
  }
}
