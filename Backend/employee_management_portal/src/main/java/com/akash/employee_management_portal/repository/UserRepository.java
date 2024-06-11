package com.akash.employee_management_portal.repository;

import com.akash.employee_management_portal.dto.EmployeeProjectDTO;
import com.akash.employee_management_portal.dto.EmployeeSkillDTO;
import com.akash.employee_management_portal.dto.FilterEmployeeDTO;
import com.akash.employee_management_portal.dto.ManagerProjectDTO;
import com.akash.employee_management_portal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);
    List<User> findByType(String type);
    List<User> findAll();

    @Query("SELECT new com.akash.employee_management_portal.dto.EmployeeSkillDTO(u.firstName, s.skillName) " +
            "FROM User u " +
            "INNER JOIN EmployeeSkill es ON u.email = es.employeeEmail " +
            "INNER JOIN Skill s ON s.Id = es.skillId " +
            "WHERE u.type = 'EMPLOYEE'")
    List<EmployeeSkillDTO> findEmployeeSkills();

    @Query("SELECT new com.akash.employee_management_portal.dto.EmployeeSkillDTO(u.firstName, s.skillName) " +
            "FROM User u " +
            "INNER JOIN EmployeeSkill es ON u.email = es.employeeEmail " +
            "INNER JOIN Skill s ON s.Id = es.skillId " +
            "WHERE u.type = 'EMPLOYEE' AND u.email = :email")
    List<EmployeeSkillDTO> findEmployeeAndSkills(@Param("email") String email);

    @Query("SELECT new com.akash.employee_management_portal.dto.FilterEmployeeDTO(CONCAT(u.firstName, ' ', u.lastName), COALESCE(ep.projectId, 0L), s.skillName) " +
            "FROM User u " +
            "INNER JOIN EmployeeSkill es ON u.email = es.employeeEmail " +
            "INNER JOIN Skill s ON s.Id = es.skillId " +
            "LEFT JOIN EmployeeProject ep ON u.email = ep.employeeEmail " +
            "WHERE u.type = 'EMPLOYEE' AND s.skillName = :skillName")
    List<FilterEmployeeDTO> filterEmployeeWithSkills(@Param("skillName") String skillName);

    @Query("SELECT new com.akash.employee_management_portal.dto.FilterEmployeeDTO(CONCAT(u.firstName, ' ', u.lastName), COALESCE(ep.projectId, 0L), s.skillName) " +
            "FROM User u " +
            "INNER JOIN EmployeeSkill es ON u.email = es.employeeEmail " +
            "INNER JOIN Skill s ON s.Id = es.skillId " +
            "LEFT JOIN EmployeeProject ep ON u.email = ep.employeeEmail " +
            "WHERE u.type = 'EMPLOYEE'")
    List<FilterEmployeeDTO> allEmployeesWithSkills(@Param("skillName") String skillName);


    @Query("SELECT new com.akash.employee_management_portal.dto.EmployeeProjectDTO(u.email, COALESCE(ep.projectId, 0L)) FROM User u LEFT JOIN EmployeeProject ep ON u.email = ep.employeeEmail WHERE u.type = 'EMPLOYEE'")
    List<EmployeeProjectDTO> getEmployeesAndProjects();

    @Query("SELECT new com.akash.employee_management_portal.dto.ManagerProjectDTO(u.email, COALESCE(ep.projectId, 0L)) FROM User u LEFT JOIN ManagerProject ep ON u.email = ep.managerEmail WHERE u.type = 'MANAGER'")
    List<ManagerProjectDTO> getManagersAndProjects();

}
