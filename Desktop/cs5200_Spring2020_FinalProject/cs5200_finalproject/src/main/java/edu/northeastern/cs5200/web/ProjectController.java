package edu.northeastern.cs5200.web;

import edu.northeastern.cs5200.entities.Project;
import edu.northeastern.cs5200.services.MapValidationError;
import edu.northeastern.cs5200.services.ProjectService;
import java.util.Collections;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {

  private final ProjectService projectService;

  private final MapValidationError mapValidationError;

  public ProjectController(ProjectService projectService,
      MapValidationError mapValidationError) {
    this.projectService = projectService;
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
//	@PutMapping("/{projectId}")
//	public ResponseEntity<?> updateProject(@PathVariable String projectId,@Valid @RequestBody Project project) {
//		
//		 Project projectold = projectService.findProjectByIdentifier(projectId);
//		 Project project1 = projectService.updateProject(project);
//        return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
//	}
@PutMapping("/update/{projectId}")
public ResponseEntity<?> updateProject(@Valid @RequestBody Project project,
    @PathVariable String projectId, BindingResult result) {
  projectService.deleteProjectByIdentifier(projectId);
  ResponseEntity<?> errorMap = mapValidationError.MapValidationService(result);
  if (errorMap != null) {
    return errorMap;
  }
  Project project1 = projectService.saveOrUpdateProject(project);
  return new ResponseEntity<Project>(project1, HttpStatus.CREATED);

}

  @GetMapping("/name/{projectName}")
  public ResponseEntity<List<Project>> getProjectByName(@PathVariable String projectName) {

    Project project = projectService.findProjectByProjectName(projectName);

    return new ResponseEntity<>(Collections.singletonList(project), HttpStatus.OK);
  }

}
