package com.ayoub.employeemanagementsystem.repositories;

import com.ayoub.employeemanagementsystem.entities.Employee;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findById(SingularAttribute<AbstractPersistable, Serializable> id);


    List<Employee> findByFirstNameContainingOrLastNameContainingOrEmailContaining(String keyword, String keyword1, String keyword2);

//    List<Employee> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String query, String query1);
}
