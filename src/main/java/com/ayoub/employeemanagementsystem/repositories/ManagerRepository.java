package com.ayoub.employeemanagementsystem.repositories;

import com.ayoub.employeemanagementsystem.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager,Long> {

    List<Manager> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String firstName, String lastName, String email);
}
