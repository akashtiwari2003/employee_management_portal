package com.akash.employee_management_portal;

import com.akash.employee_management_portal.entity.EmployeeProject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class EmployeeProjectTest {

    @Test
    void testNoArgsConstructor() {
        EmployeeProject employeeProject = new EmployeeProject();
        assertNull(employeeProject.getEmployeeEmail());
        assertEquals(0, employeeProject.getProjectId());
    }

    @Test
    void testAllArgsConstructor() {
        String employeeEmail = "employee@example.com";
        long projectId = 12345L;

        EmployeeProject employeeProject = new EmployeeProject(employeeEmail, projectId);

        assertEquals(employeeEmail, employeeProject.getEmployeeEmail());
        assertEquals(projectId, employeeProject.getProjectId());
    }

    @Test
    void testSetEmployeeEmail() {
        EmployeeProject employeeProject = new EmployeeProject();
        String employeeEmail = "employee@example.com";
        employeeProject.setEmployeeEmail(employeeEmail);
        assertEquals(employeeEmail, employeeProject.getEmployeeEmail());
    }

    @Test
    void testSetProjectId() {
        EmployeeProject employeeProject = new EmployeeProject();
        long projectId = 12345L;
        employeeProject.setProjectId(projectId);
        assertEquals(projectId, employeeProject.getProjectId());
    }

    @Test
    void testGetEmployeeEmail() {
        String employeeEmail = "employee@example.com";
        EmployeeProject employeeProject = new EmployeeProject(employeeEmail, 12345L);
        assertEquals(employeeEmail, employeeProject.getEmployeeEmail());
    }

    @Test
    void testGetProjectId() {
        long projectId = 12345L;
        EmployeeProject employeeProject = new EmployeeProject("employee@example.com", projectId);
        assertEquals(projectId, employeeProject.getProjectId());
    }
}
