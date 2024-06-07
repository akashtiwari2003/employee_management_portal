package com.akash.employee_management_portal.rest;

import com.akash.employee_management_portal.dto.SkillRequest;
import com.akash.employee_management_portal.entity.Skill;
import com.akash.employee_management_portal.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SkillController {
    @Autowired
    private SkillService skillService;

    @GetMapping("/skills")
    public List<Skill> findAllSkills(){
        return skillService.findAllSkills();
    }

    @PostMapping("/skill")
    public ResponseEntity<String> createSkill(@RequestBody SkillRequest skillRequest){
        return skillService.createSkill(skillRequest);
    }
}
