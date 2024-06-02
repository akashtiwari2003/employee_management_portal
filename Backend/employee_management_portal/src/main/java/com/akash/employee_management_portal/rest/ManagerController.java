package com.akash.employee_management_portal.rest;

import com.akash.employee_management_portal.dto.ManagerProjectDTO;
import com.akash.employee_management_portal.service.ManagerProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagerController {

    @Autowired
    ManagerProjectService managerProjectService;

    @PostMapping("/assignmanager")
    public ResponseEntity<String> assignManager(@RequestBody ManagerProjectDTO managerProjectDTO){
        return managerProjectService.assignManager(managerProjectDTO);
    }
}
