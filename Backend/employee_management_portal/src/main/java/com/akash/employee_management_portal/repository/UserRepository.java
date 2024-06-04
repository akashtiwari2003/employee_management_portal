package com.akash.employee_management_portal.repository;

import com.akash.employee_management_portal.dto.EmployeeProjectDTO;
import com.akash.employee_management_portal.dto.EmployeeSkillDTO;
import com.akash.employee_management_portal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmailAndPassword(String email, String password);
    List<User> findByType(String type);
    List<User> findAll();

    @Query("SELECT new com.akash.employee_management_portal.dto.EmployeeSkillDTO(u.firstName, s.skillName) " +
            "FROM User u " +
            "INNER JOIN EmployeeSkill es ON u.email = es.employeeEmail " +
            "INNER JOIN Skill s ON s.Id = es.skillId " +
            "WHERE u.type = 'employee'")
    List<EmployeeSkillDTO> findEmployeeSkills();

    @Query("SELECT new com.akash.employee_management_portal.dto.EmployeeSkillDTO(u.firstName, s.skillName) " +
            "FROM User u " +
            "INNER JOIN EmployeeSkill es ON u.email = es.employeeEmail " +
            "INNER JOIN Skill s ON s.Id = es.skillId " +
            "WHERE u.type = 'employee' AND s.skillName = ?1")
    List<EmployeeSkillDTO> filterEmployeeWithSkills(String skillName);

    @Query("SELECT new com.akash.employee_management_portal.dto.EmployeeProjectDTO(u.email, COALESCE(ep.projectId, 0L)) FROM User u LEFT JOIN EmployeeProject ep ON u.email = ep.employeeEmail WHERE u.type = 'employee'")
    List<EmployeeProjectDTO> getEmployeesAndProjects();

}
