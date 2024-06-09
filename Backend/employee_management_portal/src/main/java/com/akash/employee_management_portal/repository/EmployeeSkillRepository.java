package com.akash.employee_management_portal.repository;

import com.akash.employee_management_portal.entity.EmployeeSkill;
import com.akash.employee_management_portal.entity.compositekeys.EmployeeSkillCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, EmployeeSkillCompositeKey> {
    void deleteById(EmployeeSkillCompositeKey employeeSkillCompositeKey);
}
