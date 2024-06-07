package com.akash.employee_management_portal.rest;

import com.akash.employee_management_portal.dto.AddSkillDTO;
import com.akash.employee_management_portal.service.EmployeeSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeSkillController {
    @Autowired
    EmployeeSkillService employeeSkillService;

    @PostMapping("/addskill")
    public ResponseEntity<String> addSkill(@RequestBody AddSkillDTO addSkillDTO){
        return employeeSkillService.addSkill(addSkillDTO);
    }

}
