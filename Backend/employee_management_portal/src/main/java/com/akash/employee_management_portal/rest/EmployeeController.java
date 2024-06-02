package com.akash.employee_management_portal.rest;

import com.akash.employee_management_portal.dto.EmployeeProjectDTO;
import com.akash.employee_management_portal.service.EmployeeProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeProjectService employeeProjectService;

    @PostMapping("/assignemployee")
    public ResponseEntity<String> assignEmployee(@RequestBody EmployeeProjectDTO employeeProjectDTO){
        return employeeProjectService.assignEmployee(employeeProjectDTO);
    }
}
