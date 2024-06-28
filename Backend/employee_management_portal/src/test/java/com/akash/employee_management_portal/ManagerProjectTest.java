package com.akash.employee_management_portal;

import com.akash.employee_management_portal.entity.ManagerProject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ManagerProjectTest {

    @Test
    void testNoArgsConstructor() {
        ManagerProject managerProject = new ManagerProject();
        assertNull(managerProject.getManagerEmail());
        assertEquals(0, managerProject.getProjectId());
    }

    @Test
    void testAllArgsConstructor() {
        String managerEmail = "manager@example.com";
        long projectId = 12345L;

        ManagerProject managerProject = new ManagerProject(managerEmail, projectId);

        assertEquals(managerEmail, managerProject.getManagerEmail());
        assertEquals(projectId, managerProject.getProjectId());
    }

    @Test
    void testSetManagerEmail() {
        ManagerProject managerProject = new ManagerProject();
        String managerEmail = "manager@example.com";
        managerProject.setManagerEmail(managerEmail);
        assertEquals(managerEmail, managerProject.getManagerEmail());
    }

    @Test
    void testSetProjectId() {
        ManagerProject managerProject = new ManagerProject();
        long projectId = 12345L;
        managerProject.setProjectId(projectId);
        assertEquals(projectId, managerProject.getProjectId());
    }

    @Test
    void testGetManagerEmail() {
        String managerEmail = "manager@example.com";
        ManagerProject managerProject = new ManagerProject(managerEmail, 12345L);
        assertEquals(managerEmail, managerProject.getManagerEmail());
    }

    @Test
    void testGetProjectId() {
        long projectId = 12345L;
        ManagerProject managerProject = new ManagerProject("manager@example.com", projectId);
        assertEquals(projectId, managerProject.getProjectId());
    }
}
