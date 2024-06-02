package com.akash.employee_management_portal.service;

import com.akash.employee_management_portal.dto.EmployeeProjectDTO;
import com.akash.employee_management_portal.entity.EmployeeProject;
import com.akash.employee_management_portal.repository.EmployeeProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeProjectServiceImpl implements EmployeeProjectService{

    @Autowired
    EmployeeProjectRepository employeeProjectRepository;

    @Override
    public ResponseEntity<String> assignEmployee(EmployeeProjectDTO employeeProjectDTO) {
        EmployeeProject employeeProject = new EmployeeProject(employeeProjectDTO.getEmployeeEmail(), employeeProjectDTO.getProjectId());
        employeeProjectRepository.save(employeeProject);
        return ResponseEntity.ok("Project Assigned to Employee");
    }
}