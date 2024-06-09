package com.akash.employee_management_portal.service;

import com.akash.employee_management_portal.dto.SkillDTO;
import com.akash.employee_management_portal.dto.SkillRequest;
import com.akash.employee_management_portal.entity.Skill;
import com.akash.employee_management_portal.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<SkillDTO> findAllSkills() {
        List<Skill> skills = skillRepository.findAll();
        return skills.stream().sorted(Comparator.comparing(Skill::getId)).map(skill -> new SkillDTO(skill.getId(), skill.getSkillName())).collect(Collectors.toList());
    }
}
