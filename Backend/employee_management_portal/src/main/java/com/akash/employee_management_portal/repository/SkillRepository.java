package com.akash.employee_management_portal.repository;

import com.akash.employee_management_portal.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    Skill findBySkillName(String skillName);
    List<Skill> findAll();
}
