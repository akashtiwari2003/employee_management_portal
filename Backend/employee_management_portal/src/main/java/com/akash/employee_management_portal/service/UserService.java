package com.akash.employee_management_portal.service;

import com.akash.employee_management_portal.dto.*;
import com.akash.employee_management_portal.entity.User;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    User findByEmail(String userEmail);
    ResponseEntity<User> findByEmailAndPassword(String email, String password);
    ResponseEntity<String> registerUser(RegistrationRequest registrationRequest);
    ResponseEntity<String> updateUser(RegistrationRequest updateRequest);
    void deleteEmployeeByEmail(String email);
    List<User> findByType(String type);
    List<User> findAll();
    List<EmployeeSkillDTO> findEmployeeSkills();
    List<FilterEmployeeDTO> filterEmployeeWithSkills(String skill);
    List<EmployeeProjectDTO> getEmployeesAndProjects();
    List<ManagerProjectDTO> getManagersAndProjects();
    List<EmployeeSkillDTO> findEmployeeAndSkills(String email);
}
