package edu.northeastern.cs5200.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

@Entity
public class Engineer extends User {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int teamId;
  @ManyToOne()
  @JsonIgnore
  private Team engineerToTeam;

  public Engineer() {
    super();
  }

  public Engineer(int id, String fullName, String password, String email, String userRole,
      String userName) {
    super();
    this.engineerToTeam = engineerToTeam;
  }

  public Team getEngineerToTeam() {
    return engineerToTeam;
  }

  public void setToCurse(Team team) {
    this.engineerToTeam = team;
  }

  public int getTeamId() {
    return teamId;
  }

  public void setTeamId(int teamId) {
    this.teamId = teamId;
  }
}
