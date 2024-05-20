package com.ayoub.employeemanagementsystem.services;

import com.ayoub.employeemanagementsystem.entities.Employee;
import com.ayoub.employeemanagementsystem.repositories.EmployeeRepository;
import com.ayoub.employeemanagementsystem.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ManagerRepository managerRepository;




    @Override
    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(long Id) {
        Optional<Employee> optional =employeeRepository.findById(Id);
        Employee employee = null;
        if(optional.isPresent()) {
            employee=optional.get();}else{
            throw new RuntimeException("employee not found by id : "+Id);
        }
        return employee;
    }
    @Override
    public void deleteEmployeeById(long id){
        this.employeeRepository.deleteById(id);
    }

    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize,String sortField,String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable= PageRequest.of(pageNo -1,pageSize);
        return this.employeeRepository.findAll(pageable);
    }

//    @Override
//    public List<Employee> search(String query) {
//        // Assuming you want to search by first name or last name
//        return employeeRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(query, query);
//    }
    @Override
    public List<Employee> searchEmployees(String keyword) {
        return employeeRepository.findByFirstNameContainingOrLastNameContainingOrEmailContaining(keyword, keyword, keyword);
    }

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
@Override
    public List<Employee> searchEmployeesByName(String keyword) {
    return employeeRepository.findByFirstNameContainingIgnoreCase(keyword);
}

}