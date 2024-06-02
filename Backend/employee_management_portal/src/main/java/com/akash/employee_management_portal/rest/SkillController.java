package com.akash.employee_management_portal.rest;

import com.akash.employee_management_portal.dto.SkillRequest;
import com.akash.employee_management_portal.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SkillController {
    @Autowired
    private SkillService skillService;

    @PostMapping("/skill")
    public ResponseEntity<String> createSkill(@RequestBody SkillRequest skillRequest){
        return skillService.createSkill(skillRequest);
    }
}
