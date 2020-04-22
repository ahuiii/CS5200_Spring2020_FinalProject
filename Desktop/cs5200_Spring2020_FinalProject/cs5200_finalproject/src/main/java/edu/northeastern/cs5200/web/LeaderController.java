package edu.northeastern.cs5200.web;

import edu.northeastern.cs5200.entities.Engineer;
import edu.northeastern.cs5200.entities.Project;
import edu.northeastern.cs5200.services.EngineerService;
import edu.northeastern.cs5200.services.MapValidationError;
import edu.northeastern.cs5200.services.ProjectService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/leader")
public class LeaderController {

  private final ProjectService projectService;

  private final EngineerService engineerService;

  private final MapValidationError mapValidationError;

  public LeaderController(ProjectService projectService,EngineerService engineerService,
      MapValidationError mapValidationError) {
    this.projectService = projectService;
    this.engineerService = engineerService;
    this.mapValidationError = mapValidationError;
  }

  @PostMapping("")
  public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project,
      BindingResult result) {

    ResponseEntity<?> errorMap = mapValidationError.MapValidationService(result);
    if (errorMap != null) {
      return errorMap;
    }

    Project project1 = projectService.saveOrUpdateProject(project);
    return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
  }

  @GetMapping("/{projectId}")
  public ResponseEntity<?> getProjectById(@PathVariable String projectId) {

    Project project = projectService.findProjectByIdentifier(projectId);

    return new ResponseEntity<Project>(project, HttpStatus.OK);
  }

  @GetMapping("/all")
  public Iterable<Project> getAllProjects() {
    return projectService.findAllProjects();
  }

  @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId){
        projectService.deleteProjectByIdentifier(projectId);

        return new ResponseEntity<String>("Project with ID: '"+projectId+"' was deleted", HttpStatus.OK);
    }

  @GetMapping("/engineer/all")
  public Iterable<Engineer> getAllEngineer() {

    return engineerService.getAllEngineer();
  }

  @GetMapping("/engineer/{engineerId}")
  public ResponseEntity<?> getEngineerById(@PathVariable int engineerId) {

    Engineer engineer = engineerService.findEngineerById(engineerId);

    return new ResponseEntity<Project>((MultiValueMap<String, String>) engineer, HttpStatus.OK);
  }

  @DeleteMapping("/engineer/{engineerId}")
  public ResponseEntity<?> deleteEngineer(@PathVariable int engineerId){
    engineerService.deleteEngineerById(engineerId);

    return new ResponseEntity<String>("Engineer with ID: '"+engineerId+"' was deleted", HttpStatus.OK);
  }

}
