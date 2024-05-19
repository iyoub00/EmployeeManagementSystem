package com.ayoub.employeemanagementsystem.services;

import com.ayoub.employeemanagementsystem.entities.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeById(long Id);

    void deleteEmployeeById(long id);

//    Employee getEmployeeById(Long Id);
    Page<Employee> findPaginated(int pageNo, int pageSize,String sortField,String sortDirection);

    List<Employee> searchEmployees(String keyword);

    //List<Employee> search(String query);
}
