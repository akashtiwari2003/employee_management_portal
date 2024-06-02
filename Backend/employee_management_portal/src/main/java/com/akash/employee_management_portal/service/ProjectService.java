package com.akash.employee_management_portal.service;

import com.akash.employee_management_portal.dto.ProjectDTO;
import com.akash.employee_management_portal.dto.RegistrationRequest;
import com.akash.employee_management_portal.entity.Project;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProjectService {
    List<Project> findAll();
    ResponseEntity<String> createProject(ProjectDTO projectDTO);

}
