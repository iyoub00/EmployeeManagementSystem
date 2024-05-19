package com.ayoub.employeemanagementsystem.services.impl;

import com.ayoub.employeemanagementsystem.entities.Department;
import com.ayoub.employeemanagementsystem.entities.Employee;
import com.ayoub.employeemanagementsystem.repositories.DepartmentRepository;
import com.ayoub.employeemanagementsystem.repositories.EmployeeRepository;
import com.ayoub.employeemanagementsystem.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<Department> searchDepartmentsByName(String name) {
        return departmentRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found"));
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(long id, Department department) {
        Department existingDepartment = getDepartmentById(id);
        existingDepartment.setName(department.getName());
        existingDepartment.setManager(department.getManager());
        return departmentRepository.save(existingDepartment);
    }

    @Override
    public void deleteDepartment(long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public void addEmployeeToDepartment(long departmentId, long employeeId) {
        Department department = getDepartmentById(departmentId);
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        department.getEmployees().add(employee);
        employee.setDepartment(department);
        departmentRepository.save(department);
    }

    @Override
    public void removeEmployeeFromDepartment(long departmentId, long employeeId) {
        Department department = getDepartmentById(departmentId);
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        department.getEmployees().remove(employee);
        employee.setDepartment(null);
        departmentRepository.save(department);
    }
}
