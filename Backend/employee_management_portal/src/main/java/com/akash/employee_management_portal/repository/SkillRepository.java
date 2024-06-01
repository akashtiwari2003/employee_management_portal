package com.akash.employee_management_portal.repository;

import com.akash.employee_management_portal.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}
