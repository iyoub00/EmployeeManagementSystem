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
        return teamRepository.findById(id).orElse(null);
    }

    @Override
    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team updateTeam(long id, Team team) {
        if (teamRepository.existsById(id)) {
            team.setId(id);
            return teamRepository.save(team);
        }
        return null;
    }

    @Override
    public void deleteTeam(long id) {
        teamRepository.deleteById(id);
    }
}
