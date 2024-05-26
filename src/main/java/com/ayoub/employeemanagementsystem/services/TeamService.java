package com.ayoub.employeemanagementsystem.services;

import com.ayoub.employeemanagementsystem.entities.Team;

import java.util.List;

public interface TeamService {
    List<Team> getAllTeams();

    Team getTeamById(long id);

    Team createTeam(Team team);

    //    @Override
    //    public Team createTeam(Team team) {
    //        return teamRepository.save(team);
    //    }
    Team createTeam(String teamName, Long projectId);

    Team updateTeam(long id, Team team);

    void deleteTeam(long id);
}
