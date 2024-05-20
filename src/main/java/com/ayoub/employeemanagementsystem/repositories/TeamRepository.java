package com.ayoub.employeemanagementsystem.repositories;

import com.ayoub.employeemanagementsystem.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {

}
