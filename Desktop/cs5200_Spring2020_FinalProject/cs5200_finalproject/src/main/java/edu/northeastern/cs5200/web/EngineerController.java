package edu.northeastern.cs5200.web;

import edu.northeastern.cs5200.entities.Project;
import edu.northeastern.cs5200.entities.Team;
import edu.northeastern.cs5200.services.ProjectService;
import javax.validation.Valid;

import edu.northeastern.cs5200.services.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/engineer")
public class EngineerController {

  private final ProjectService projectService;
  private final TeamService teamService;
  public EngineerController(ProjectService projectService,TeamService teamService) {
    this.projectService = projectService;
    this.teamService = teamService;
  }

  @PutMapping("/update/{projectId}")
  public ResponseEntity<?> updateProject(@Valid @RequestBody Project project,
      @PathVariable String projectId) {
    projectService.deleteProjectByIdentifier(projectId);
    Project project1 = projectService.saveOrUpdateProject(project);
    return new ResponseEntity<Project>(project1, HttpStatus.CREATED);

  }
  @GetMapping("/team/{team_id}")

  public ResponseEntity<?> findTeamById(@PathVariable int team_id){

    Team team =  teamService.findTeamById(team_id);
    return new ResponseEntity<Team>(team,HttpStatus.OK);

  }
  @GetMapping("/project/{projectId}")
  public ResponseEntity<?> getProjectById(@PathVariable String projectId) {

    Project project = projectService.findProjectByIdentifier(projectId);

    return new ResponseEntity<Project>(project, HttpStatus.OK);
  }

}
