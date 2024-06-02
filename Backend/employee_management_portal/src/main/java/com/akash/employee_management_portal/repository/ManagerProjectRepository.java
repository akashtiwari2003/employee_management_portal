package com.akash.employee_management_portal.repository;

import com.akash.employee_management_portal.entity.ManagerProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerProjectRepository extends JpaRepository<ManagerProject, String> {
    ManagerProject findByManagerEmail(String managerEmail);
    ManagerProject findByProjectId(long projectId);
}
