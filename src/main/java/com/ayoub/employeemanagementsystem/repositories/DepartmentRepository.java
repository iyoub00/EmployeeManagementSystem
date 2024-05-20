package com.ayoub.employeemanagementsystem.repositories;

import com.ayoub.employeemanagementsystem.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {

    List<Department> findByNameContainingIgnoreCase(String name);
}
