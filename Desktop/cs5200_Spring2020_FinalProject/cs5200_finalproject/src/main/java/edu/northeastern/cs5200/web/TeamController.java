package edu.northeastern.cs5200.web;

import edu.northeastern.cs5200.entities.Engineer;
import edu.northeastern.cs5200.entities.Project;
import edu.northeastern.cs5200.services.ProjectService;
import edu.northeastern.cs5200.services.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/team")
public class TeamController {
    private final TeamService teamService;
    private final ProjectService projectService;
    public TeamController(TeamService teamService,ProjectService projectService){
        this.teamService = teamService;
        this.projectService = projectService;
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId) {

        Project project = projectService.findByProjectIdentifier(projectId);

        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("/engineer/{teamId}")
    public Iterable<Engineer> findEngineerByTeamId(@PathVariable int teamId){

         return teamService.findEngineerByteamId(teamId);

    }

}
