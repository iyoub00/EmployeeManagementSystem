package com.ayoub.employeemanagementsystem.services;

import com.ayoub.employeemanagementsystem.entities.Manager;
import com.ayoub.employeemanagementsystem.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService{
    @Autowired
    private ManagerRepository managerRepository;


    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    public void saveManager(Manager manager) {
        managerRepository.save(manager);
    }

    public Manager getManagerById(long id) {
        return managerRepository.findById(id).orElseThrow(() -> new RuntimeException("Manager not found"));
    }

    public void deleteManagerById(long id) {
        managerRepository.deleteById(id);
    }

    public Page<Manager> findPaginated(int pageNo, int pageSize, String sortField, String sortDir) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
        return managerRepository.findAll(pageable);
    }

    @Override
    public List<Manager> search(String query) {
        // Implement your search logic here, e.g., search by first name, last name, or email
        return managerRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(query, query, query);
    }
}
