package com.ayoub.employeemanagementsystem.services;

import com.ayoub.employeemanagementsystem.entities.Team;

import java.util.List;

public interface TeamService {
    List<Team> getAllTeams();

    Team getTeamById(long id);

    Team createTeam(Team team);

    Team createTeam(String name, Long projectId);

    Team updateTeam(long id, Team team);

    void deleteTeam(long id);
}
