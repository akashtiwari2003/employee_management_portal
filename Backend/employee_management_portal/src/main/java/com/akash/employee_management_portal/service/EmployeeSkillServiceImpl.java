package com.akash.employee_management_portal.service;

import com.akash.employee_management_portal.dto.AddSkillDTO;
import com.akash.employee_management_portal.dto.EmployeeSkillDTO;
import com.akash.employee_management_portal.entity.EmployeeSkill;
import com.akash.employee_management_portal.entity.Skill;
import com.akash.employee_management_portal.entity.compositekeys.EmployeeSkillCompositeKey;
import com.akash.employee_management_portal.repository.EmployeeSkillRepository;
import com.akash.employee_management_portal.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeSkillServiceImpl implements EmployeeSkillService{
    @Autowired
    EmployeeSkillRepository employeeSkillRepository;

    @Autowired
    SkillRepository skillRepository;

    @Override
    public ResponseEntity<String> addSkill(AddSkillDTO addSkillDTO) {
        Skill skill = skillRepository.findBySkillName(addSkillDTO.getSkillName());
        EmployeeSkillCompositeKey employeeSkillCompositeKey = new EmployeeSkillCompositeKey(addSkillDTO.getEmployeeEmail(), skill.getId());
        EmployeeSkill employeeSkill = new EmployeeSkill(employeeSkillCompositeKey);
        employeeSkillRepository.save(employeeSkill);
        return ResponseEntity.ok("Skill Added Successfully");
    }

    @Override
    public void deleteById(EmployeeSkillCompositeKey employeeSkillCompositeKey) {
        employeeSkillRepository.deleteById(employeeSkillCompositeKey);
    }
}
