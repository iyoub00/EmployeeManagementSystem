package com.ayoub.employeemanagementsystem.services;

import com.ayoub.employeemanagementsystem.entities.Project;

import java.util.List;

public interface ProjectService {
    List<Project> getAllProjects();
    Project createProject(Project project);

    Project getProjectById(long id);
}
