package com.akash.employee_management_portal;

import com.akash.employee_management_portal.dto.ProjectDTO;
import com.akash.employee_management_portal.entity.Project;
import com.akash.employee_management_portal.repository.ProjectRepository;
import com.akash.employee_management_portal.service.ProjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Project> projects = List.of(
                new Project("Project 1", "Description 1"),
                new Project("Project 2", "Description 2")
        );

        when(projectRepository.findAll()).thenReturn(projects);

        List<Project> result = projectService.findAll();

        assertEquals(2, result.size());
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    void testCreateProject() {
        ProjectDTO projectDTO = new ProjectDTO();
        Project project = new Project("New Project", "New Description");
        Project createdProject = new Project("New Project", "New Description");
        createdProject.setProjectId(1L);

        when(projectRepository.save(any(Project.class))).thenReturn(project);
        when(projectRepository.findByProjectName(any(String.class))).thenReturn(createdProject);

        ResponseEntity<String> response = projectService.createProject(projectDTO);

        assertNotNull(response);
        assertEquals("Project Created with ID : 1", response.getBody());
        verify(projectRepository, times(1)).save(any(Project.class));
        verify(projectRepository, times(1)).findByProjectName(any(String.class));
    }

    @Test
    void testRemoveProject() {
        Project project = new Project("Project to be removed", "Description");
        project.setProjectId(1L);

        when(projectRepository.findByProjectId(1L)).thenReturn(project);
        doNothing().when(projectRepository).delete(project);

        ResponseEntity<String> response = projectService.removeProject(1L);

        assertEquals("Project Removed", response.getBody());
        verify(projectRepository, times(1)).findByProjectId(1L);
        verify(projectRepository, times(1)).delete(project);
    }
}
