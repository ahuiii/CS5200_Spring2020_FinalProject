package edu.northeastern.cs5200.services;

import edu.northeastern.cs5200.entities.Engineer;
import edu.northeastern.cs5200.entities.Team;
import edu.northeastern.cs5200.exceptions.ProjectIdException;
import edu.northeastern.cs5200.repository.TeamRepository;
import org.springframework.stereotype.Service;


@Service
public class TeamService {

  private final TeamRepository teamRepository;

  public TeamService(TeamRepository teamRepository) {

    this.teamRepository = teamRepository;
  }

  public Team findByProjectId(int id) {
    return teamRepository.findByProjectId(id);
  }

  public Team findTeamById(int id) {

    Team team = teamRepository.findById(id);

    if (team == null) {
      throw new ProjectIdException("Team ID '" + id + "' does not exist");

    }
    return team;
  }

  public void deleteTeamById(int id) {
    Team team = teamRepository.findById(id);
    teamRepository.delete(team);
  }

  public Team createTeam(Team team) {
    return teamRepository.save(team);
  }

  public Team updateTeamById(Team team) {

    return createTeam(team);

  }

  public Iterable<Engineer> findEngineerByteamId(int teamId) {

    return teamRepository.findByEngineerInTeam(teamId);
  }
}
