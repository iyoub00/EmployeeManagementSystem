package com.ayoub.employeemanagementsystem.controllers;

import com.ayoub.employeemanagementsystem.entities.Project;
import com.ayoub.employeemanagementsystem.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/create")
    public String showCreateProjectForm(Model model) {
        model.addAttribute("project", new Project());
        return "project-form"; // Thymeleaf template name
    }

    @PostMapping("/create")
    public String createProject(@Valid Project project, BindingResult result) {
        if (result.hasErrors()) {
            return "project-form"; // Return form with errors
        }
        projectService.createProject(project);
        return "redirect:/projects"; // Redirect to project list page
    }

    @GetMapping
    public String listProjects(Model model) {
        model.addAttribute("projects", projectService.getAllProjects());
        return "projects"; // Thymeleaf template name for listing projects
    }
}