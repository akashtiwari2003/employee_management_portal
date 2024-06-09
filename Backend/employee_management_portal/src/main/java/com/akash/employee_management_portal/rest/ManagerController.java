package com.akash.employee_management_portal.rest;

import com.akash.employee_management_portal.dto.AvailableProjectDTO;
import com.akash.employee_management_portal.dto.ManagerProjectDTO;
import com.akash.employee_management_portal.service.ManagerProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ManagerController {

    @Autowired
    ManagerProjectService managerProjectService;

    @PostMapping("/assignmanager")
    public ResponseEntity<String> assignManager(@RequestBody ManagerProjectDTO managerProjectDTO){
        return managerProjectService.assignManager(managerProjectDTO);
    }

    @DeleteMapping("/unassign/{managerEmail}")
    public ResponseEntity<String> unassignManager(@PathVariable("managerEmail") String managerEmail){
        return managerProjectService.unassignManager(managerEmail);
    }

    @GetMapping("/assignmanagerprojects")
    List<AvailableProjectDTO> findProjectsNotManaged(){
        return managerProjectService.findProjectsNotManaged();
    }
}
