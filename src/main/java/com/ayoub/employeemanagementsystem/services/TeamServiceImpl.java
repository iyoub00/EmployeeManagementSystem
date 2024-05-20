package com.ayoub.employeemanagementsystem.services;

import com.ayoub.employeemanagementsystem.entities.Team;
import com.ayoub.employeemanagementsystem.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team getTeamById(long id) {
        return teamRepository.findById(id).orElseThrow(() -> new RuntimeException("Team not found"));
    }

    @Override
    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team updateTeam(long id, Team team) {
        Team existingTeam = getTeamById(id);
        existingTeam.setName(team.getName());
        existingTeam.setEmployees(team.getEmployees());
        return teamRepository.save(existingTeam);
    }

    @Override
    public void deleteTeam(long id) {
        teamRepository.deleteById(id);
    }
}
