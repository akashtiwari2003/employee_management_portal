package com.akash.employee_management_portal;

import com.akash.employee_management_portal.dto.ProjectDTO;
import com.akash.employee_management_portal.entity.Project;
import com.akash.employee_management_portal.rest.ProjectController;
import com.akash.employee_management_portal.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProjectControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
    }

    @Test
    void testGetProducts() throws Exception {
        List<Project> projects = List.of(
                new Project("Project 1", "Description 1"),
                new Project("Project 2", "Description 2")
        );

        when(projectService.findAll()).thenReturn(projects);

        mockMvc.perform(get("/projects/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].projectName").value("Project 1"))
                .andExpect(jsonPath("$[1].projectName").value("Project 2"));

        verify(projectService, times(1)).findAll();
    }

    @Test
    void testCreateProject() throws Exception {
        ProjectDTO projectDTO = new ProjectDTO();

        when(projectService.createProject(any(ProjectDTO.class))).thenReturn(ResponseEntity.ok("Project Created with ID : 1"));

        mockMvc.perform(post("/projects/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"projectName\":\"New Project\", \"projectDesc\":\"New Description\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Project Created with ID : 1"));

        verify(projectService, times(1)).createProject(any(ProjectDTO.class));
    }

    @Test
    void testRemoveProject() throws Exception {
        long projectId = 1L;

        when(projectService.removeProject(projectId)).thenReturn(ResponseEntity.ok("Project Removed"));

        mockMvc.perform(delete("/projects/remove/{projectId}", projectId))
                .andExpect(status().isOk())
                .andExpect(content().string("Project Removed"));

        verify(projectService, times(1)).removeProject(projectId);
    }
}
