package com.ayoub.employeemanagementsystem.services;

import com.ayoub.employeemanagementsystem.entities.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAllDepartments();
    Department getDepartmentById(long id);
    Department createDepartment(Department department);
    Department updateDepartment(long id, Department department);
    void deleteDepartment(long id);
    void addEmployeeToDepartment(long departmentId, long employeeId);
    void removeEmployeeFromDepartment(long departmentId, long employeeId);
    List<Department> searchDepartmentsByName(String name);
}
