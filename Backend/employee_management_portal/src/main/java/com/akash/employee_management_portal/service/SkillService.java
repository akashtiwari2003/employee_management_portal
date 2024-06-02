package com.akash.employee_management_portal.service;

import com.akash.employee_management_portal.dto.SkillRequest;
import org.springframework.http.ResponseEntity;

public interface SkillService {
    ResponseEntity<String> createSkill(SkillRequest skillRequest);
}
