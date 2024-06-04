package com.akash.employee_management_portal.repository;

import com.akash.employee_management_portal.entity.Project;
import com.akash.employee_management_portal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAll();
    Project findByProjectId(long projectId);
    Project findByProjectName(String projectName);
}
