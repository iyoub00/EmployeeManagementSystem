package com.ayoub.employeemanagementsystem.services;

import com.ayoub.employeemanagementsystem.entities.Manager;
import org.springframework.data.domain.Page;

public interface ManagerService {
    Object getAllManagers();

    void saveManager(Manager manager);

    Manager getManagerById(long id);

    void deleteManagerById(long id);

    Page<Manager> findPaginated(int pageNo, int pageSize, String sortField, String sortDir);
}
