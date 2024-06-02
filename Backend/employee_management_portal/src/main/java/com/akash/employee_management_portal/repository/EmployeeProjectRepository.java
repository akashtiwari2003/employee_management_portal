package com.akash.employee_management_portal.repository;

import com.akash.employee_management_portal.entity.EmployeeProject;
import com.akash.employee_management_portal.entity.ManagerProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, String> {
    EmployeeProject findByEmployeeEmail(String employeeEmail);
}
