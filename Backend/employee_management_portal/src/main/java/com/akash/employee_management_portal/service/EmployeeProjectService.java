package com.akash.employee_management_portal.service;

import com.akash.employee_management_portal.dto.EmployeeProjectDTO;
import org.springframework.http.ResponseEntity;

public interface EmployeeProjectService {
    ResponseEntity<String> assignEmployee(EmployeeProjectDTO employeeProjectDTO);
    ResponseEntity<String> unassignEmployee(String employeeEmail);

}
