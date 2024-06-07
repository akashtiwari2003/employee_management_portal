package com.akash.employee_management_portal.service;

import com.akash.employee_management_portal.dto.SkillRequest;
import com.akash.employee_management_portal.entity.Skill;
import com.akash.employee_management_portal.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService{

    @Autowired
    SkillRepository skillRepository;

    @Override
    public ResponseEntity<String> createSkill(SkillRequest skillRequest) {
        Skill skill = new Skill(skillRequest.getSkillName());
        skillRepository.save(skill);
        return ResponseEntity.ok("Skill Created");
    }

    @Override
    public List<Skill> findAllSkills() {
        return skillRepository.findAll();
    }
}
