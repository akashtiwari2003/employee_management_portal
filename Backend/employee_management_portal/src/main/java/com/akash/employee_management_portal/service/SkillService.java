package com.akash.employee_management_portal.service;

import com.akash.employee_management_portal.dto.SkillDTO;
import com.akash.employee_management_portal.dto.SkillRequest;
import com.akash.employee_management_portal.entity.Skill;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SkillService {
    ResponseEntity<String> createSkill(SkillRequest skillRequest);
    List<SkillDTO> findAllSkills();
}
