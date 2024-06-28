package com.akash.employee_management_portal;

import com.akash.employee_management_portal.entity.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ProjectTest {

    private Project project;

    @BeforeEach
    void setUp() {
        project = new Project();
    }

    @Test
    void testDefaultConstructor() {
        assertNull(project.getProjectName());
        assertNull(project.getProjectDesc());
        assertEquals(0, project.getProjectId());
    }

    @Test
    void testParameterizedConstructor() {
        Project parameterizedProject = new Project("AI Project", "Developing AI models");
        assertEquals("AI Project", parameterizedProject.getProjectName());
        assertEquals("Developing AI models", parameterizedProject.getProjectDesc());
    }

    @Test
    void testGetProjectId() {
        project.setProjectId(1);
        assertEquals(1, project.getProjectId());
    }

    @Test
    void testSetProjectId() {
        project.setProjectId(1);
        assertEquals(1, project.getProjectId());
    }

    @Test
    void testGetProjectName() {
        project.setProjectName("AI Project");
        assertEquals("AI Project", project.getProjectName());
    }

    @Test
    void testSetProjectName() {
        project.setProjectName("AI Project");
        assertEquals("AI Project", project.getProjectName());
    }

    @Test
    void testGetProjectDesc() {
        project.setProjectDesc("Developing AI models");
        assertEquals("Developing AI models", project.getProjectDesc());
    }

    @Test
    void testSetProjectDesc() {
        project.setProjectDesc("Developing AI models");
        assertEquals("Developing AI models", project.getProjectDesc());
    }
}
