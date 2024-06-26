package com.akash.employee_management_portal.service;

import com.akash.employee_management_portal.dto.AddSkillDTO;
import com.akash.employee_management_portal.dto.EmployeeSkillDTO;
import com.akash.employee_management_portal.entity.EmployeeSkill;
import com.akash.employee_management_portal.entity.compositekeys.EmployeeSkillCompositeKey;
import org.springframework.http.ResponseEntity;

public interface EmployeeSkillService {
    ResponseEntity<String> addSkill(AddSkillDTO addSkillDTO);
    void deleteById(EmployeeSkillCompositeKey employeeSkillCompositeKey);
}
