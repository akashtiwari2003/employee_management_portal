package com.akash.employee_management_portal.rest;

import com.akash.employee_management_portal.dto.EmployeeProjectDTO;
import com.akash.employee_management_portal.dto.ProjectDetails;
import com.akash.employee_management_portal.service.EmployeeProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeProjectService employeeProjectService;

    @PostMapping("/assignemployee")
    public ResponseEntity<String> assignEmployee(@RequestBody EmployeeProjectDTO employeeProjectDTO){
        return employeeProjectService.assignEmployee(employeeProjectDTO);
    }

    @DeleteMapping("/unassign/{employeeEmail}")
    public ResponseEntity<String> unassignManager(@PathVariable("employeeEmail") String employeeEmail){
        return employeeProjectService.unassignEmployee(employeeEmail);
    }

    @GetMapping("/employeeproject/{employeeEmail}")
    public ResponseEntity<ProjectDetails> getEmployeeProject(@PathVariable("employeeEmail") String employeeEmail){
        return employeeProjectService.getEmployeeProjects(employeeEmail);
    }
}
