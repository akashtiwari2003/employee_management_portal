package com.akash.employee_management_portal.service;

import com.akash.employee_management_portal.dto.AvailableProjectDTO;
import com.akash.employee_management_portal.dto.ManagerProjectDTO;
import com.akash.employee_management_portal.entity.ManagerProject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ManagerProjectService {
    ResponseEntity<String> assignManager(ManagerProjectDTO managerProjectDTO);
    ResponseEntity<String> unassignManager(String managerEmail);
    List<AvailableProjectDTO> findProjectsNotManaged();
    ManagerProject findByManagerEmail(String managerEmail);
}
