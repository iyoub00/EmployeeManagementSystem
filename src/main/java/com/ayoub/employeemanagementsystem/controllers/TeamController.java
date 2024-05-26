package com.ayoub.employeemanagementsystem.controllers;

import com.ayoub.employeemanagementsystem.entities.Project;
import com.ayoub.employeemanagementsystem.entities.Team;
import com.ayoub.employeemanagementsystem.services.ProjectService;
import com.ayoub.employeemanagementsystem.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;
    private final ProjectService projectService;

    @Autowired
    public TeamController(TeamService teamService, ProjectService projectService) {
        
        this.teamService = teamService;
        this.projectService = projectService;
    }

    @GetMapping
    public String getAllTeams(Model model) {
        List<Team> teams = teamService.getAllTeams();
        model.addAttribute("teams", teams);
        return "teams";
    }

    @GetMapping("/{id}")
    public String getTeamById(@PathVariable long id, Model model) {
        Team team = teamService.getTeamById(id);
        model.addAttribute("team", team);
        model.addAttribute("employees", team.getEmployees());
        return "team-detail";
    }

    @GetMapping("/new")
    public String showCreateTeamForm(Model model) {
        model.addAttribute("team", new Team());
        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("projects", projects);
        return "create-team";
    }

    @PostMapping
    public String createTeam(@ModelAttribute Team team) {
        Project project = projectService.getProjectById(team.getProject().getId());
        team.setProject(project);
        teamService.createTeam(team);
        return "redirect:/teams";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateTeamForm(@PathVariable long id, Model model) {
        Team team = teamService.getTeamById(id);
        model.addAttribute("team", team);
        return "update-team";
    }

    @PostMapping("/update/{id}")
    public String updateTeam(@PathVariable long id, @ModelAttribute Team team) {
        teamService.updateTeam(id, team);
        return "redirect:/teams";
    }

    @GetMapping("/delete/{id}")
    public String deleteTeam(@PathVariable long id) {
        teamService.deleteTeam(id);
        return "redirect:/teams";
    }
}
