package com.akash.employee_management_portal.service;

import com.akash.employee_management_portal.dto.ManagerProjectDTO;
import org.springframework.http.ResponseEntity;

public interface ManagerProjectService {
    ResponseEntity<String> assignManager(ManagerProjectDTO managerProjectDTO);
}
