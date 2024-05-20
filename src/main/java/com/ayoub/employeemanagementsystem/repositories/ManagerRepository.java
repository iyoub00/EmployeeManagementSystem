package com.ayoub.employeemanagementsystem.repositories;

import com.ayoub.employeemanagementsystem.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ManagerRepository extends JpaRepository<Manager,Long> {

    List<Manager> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String firstName, String lastName, String email);
}
