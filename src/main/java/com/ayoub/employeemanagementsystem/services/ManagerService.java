package com.ayoub.employeemanagementsystem.services;

import com.ayoub.employeemanagementsystem.entities.Manager;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ManagerService {

    List<Manager> search(String query);
    List<Manager> getAllManagers();

    void saveManager(Manager manager);

    Manager getManagerById(long id);

    void deleteManagerById(long id);

    Page<Manager> findPaginated(int pageNo, int pageSize, String sortField, String sortDir);
}
