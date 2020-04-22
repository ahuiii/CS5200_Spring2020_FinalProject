package edu.northeastern.cs5200.web;

import edu.northeastern.cs5200.entities.Project;
import edu.northeastern.cs5200.entities.ProjectTask;
import edu.northeastern.cs5200.entities.Team;
import edu.northeastern.cs5200.entities.User;
import edu.northeastern.cs5200.services.ProjectService;
import edu.northeastern.cs5200.services.ProjectTaskService;
import edu.northeastern.cs5200.services.TeamService;
import edu.northeastern.cs5200.services.UserService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

  private final ProjectService projectService;

  private final UserService UserService;

  private final TeamService teamService;

  private final ProjectTaskService projectTaskService;

  public AdminController(ProjectService projectService,
      edu.northeastern.cs5200.services.UserService userService,
      ProjectTaskService projectTaskService, TeamService teamService) {
    this.projectService = projectService;
    UserService = userService;
    this.projectTaskService = projectTaskService;
    this.teamService = teamService;
  }

  @GetMapping("/all")
  public Iterable<Project> getAllProjects() {
    return projectService.findAllProjects();
  }

  @DeleteMapping("/{projectId}")
  public ResponseEntity<?> deleteProject(@PathVariable String projectId) {
    projectService.deleteProjectByIdentifier(projectId);

    return new ResponseEntity<String>("Project with ID: '" + projectId + "' was deleted",
        HttpStatus.OK);
  }

  @PutMapping("/{projectId}")
  public ResponseEntity<?> updateProject(@Valid @RequestBody Project project,
      @PathVariable String projectId, BindingResult result) {
    projectService.deleteProjectByIdentifier(projectId);
//		 ResponseEntity<?> errorMap = mapValidationError.MapValidationService(result);
//			if(errorMap!=null) return errorMap;
    Project project1 = projectService.saveOrUpdateProject(project);
    return new ResponseEntity<Project>(project1, HttpStatus.CREATED);

  }

  @PostMapping("")
  public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project,
      BindingResult result) {

//        ResponseEntity<?> errorMap = mapValidationError.MapValidationService(result);
//        if(errorMap!=null) return errorMap;

    Project project1 = projectService.saveOrUpdateProject(project);
    return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
  }

  @GetMapping("/{projectId}")
  public ResponseEntity<?> getProjectById(@PathVariable String projectId) {

    Project project = projectService.findProjectByIdentifier(projectId);

    return new ResponseEntity<Project>(project, HttpStatus.OK);
  }

  @PostMapping("/backlog/{backlog_id}")
  public ResponseEntity<?> addPTtoBacklog(@Valid @RequestBody ProjectTask projectTask,
      BindingResult result, @PathVariable String backlog_id) {

    ProjectTask projectTask1 = projectTaskService.addProjectTask(backlog_id, projectTask);

    return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);
  }

  @GetMapping("/backlog/{backlog_id}")
  public Iterable<ProjectTask> getProjectBacklog(@PathVariable String backlog_id) {

    return projectTaskService.findBacklogById(backlog_id);

  }

  @GetMapping("/backlog/{backlog_id}/{pt_id}")
  public ResponseEntity<?> getProjectTask(@PathVariable String backlog_id,
      @PathVariable String pt_id) {
    ProjectTask projectTask = projectTaskService.findPTByProjectSequence(backlog_id, pt_id);
    return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
  }

  @PatchMapping("/backlog/{backlog_id}/{pt_id}")
  public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask,
      BindingResult result,
      @PathVariable String backlog_id, @PathVariable String pt_id) {

    ProjectTask updatedTask = projectTaskService
        .updateByProjectSequence(projectTask, backlog_id, pt_id);

    return new ResponseEntity<ProjectTask>(updatedTask, HttpStatus.OK);

  }


  @DeleteMapping("/backlog/{backlog_id}/{pt_id}")
  public ResponseEntity<?> deleteProjectTask(@PathVariable String backlog_id,
      @PathVariable String pt_id) {
    projectTaskService.deletePTByProjectSequence(backlog_id, pt_id);

    return new ResponseEntity<String>("Project Task " + pt_id + " was deleted successfully",
        HttpStatus.OK);
  }

  //user
  @PostMapping("/user")
  public ResponseEntity<?> addUser(@Valid @RequestBody User user) {
    User user1 = UserService.createUser(user);
    return new ResponseEntity<User>(user1, HttpStatus.CREATED);
  }

  @DeleteMapping("/user/{userName}")
  public ResponseEntity<?> deleteUserByName(@PathVariable String userName) {
    UserService.deleteUserByUserName(userName);
    return new ResponseEntity<String>("Project Task " + userName + " was deleted successfully",
        HttpStatus.OK);
  }

  @PatchMapping("/user/{userName}")
  public ResponseEntity<?> updateUser(@Valid @RequestBody User user,
      @PathVariable String userName) {

    User updatedUser = UserService.updateByUserName(user);

    return new ResponseEntity<User>(updatedUser, HttpStatus.OK);

  }


  //team
  @GetMapping("/team/{projectId}")
  public ResponseEntity<?> findTeamById(@PathVariable int projectId) {
    Team team = teamService.findByProjectId(projectId);
    return new ResponseEntity<Team>(team, HttpStatus.OK);

  }

  @DeleteMapping("/team/{team_id}")
  public ResponseEntity<?> deleteTeamById(@PathVariable int team_id) {
    teamService.deleteTeamById(team_id);
    return new ResponseEntity<String>("teamid " + team_id + " was deleted successfully",
        HttpStatus.OK);
  }

  @PatchMapping("/team/{team_id}")
  public ResponseEntity<?> updateTeamById(@Valid @RequestBody Team team) {

    Team updatedTeam = teamService.updateTeamById(team);

    return new ResponseEntity<Team>(updatedTeam, HttpStatus.OK);

  }


}
