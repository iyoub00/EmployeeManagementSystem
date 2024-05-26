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

    //    @Override
    //    public Employee getEmployeeById(Long Id) {
    //        Optional<Employee> optional =employeeRepository.findById(id);
    //        Employee employee = null;
    //        if(optional.isPresent()) {
    //            employee=optional.get();}else{
    //            throw new RuntimeException("employee not found by id : "+id);
    //        }
    //        return employee;
    //    }
//    List<Employee> searchEmployeesByName(String keyword);

    //List<Employee> search(String query);
}
