package com.akash.employee_management_portal;

import com.akash.employee_management_portal.dto.EmployeeProjectDTO;
import com.akash.employee_management_portal.dto.ProjectDetails;
import com.akash.employee_management_portal.entity.EmployeeProject;
import com.akash.employee_management_portal.entity.ManagerProject;
import com.akash.employee_management_portal.entity.Project;
import com.akash.employee_management_portal.repository.EmployeeProjectRepository;
import com.akash.employee_management_portal.repository.ManagerProjectRepository;
import com.akash.employee_management_portal.repository.ProjectRepository;
import com.akash.employee_management_portal.service.EmployeeProjectServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EmployeeProjectServiceImplTest {

    @Mock
    private EmployeeProjectRepository employeeProjectRepository;

    @Mock
    private ManagerProjectRepository managerProjectRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private EmployeeProjectServiceImpl employeeProjectService;

    public EmployeeProjectServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAssignEmployee() {
        EmployeeProjectDTO employeeProjectDTO = new EmployeeProjectDTO("test@example.com", 1L);
        EmployeeProject employeeProject = new EmployeeProject("test@example.com", 1L);

        when(employeeProjectRepository.save(any(EmployeeProject.class))).thenReturn(employeeProject);

        ResponseEntity<String> response = employeeProjectService.assignEmployee(employeeProjectDTO);

        assertEquals("Project Assigned to Employee", response.getBody());
        verify(employeeProjectRepository, times(1)).save(any(EmployeeProject.class));
    }

    @Test
    void testUnassignEmployee() {
        String employeeEmail = "test@example.com";
        EmployeeProject employeeProject = new EmployeeProject("test@example.com", 1L);

        when(employeeProjectRepository.findByEmployeeEmail(employeeEmail)).thenReturn(employeeProject);
        doNothing().when(employeeProjectRepository).delete(employeeProject);

        ResponseEntity<String> response = employeeProjectService.unassignEmployee(employeeEmail);

        assertEquals("Project Unassigned from Employee", response.getBody());
        verify(employeeProjectRepository, times(1)).findByEmployeeEmail(employeeEmail);
        verify(employeeProjectRepository, times(1)).delete(employeeProject);
    }

    @Test
    void testGetEmployeeProjects() {
        String employeeEmail = "test@example.com";
        EmployeeProject employeeProject = new EmployeeProject("test@example.com", 1L);
        ManagerProject managerProject = new ManagerProject("manager@example.com", 1L);
        Project project = new Project("Project Name", "Project Description");
        project.setProjectId(1L);

        when(employeeProjectRepository.findByEmployeeEmail(employeeEmail)).thenReturn(employeeProject);
        when(managerProjectRepository.findByProjectId(1L)).thenReturn(managerProject);
        when(projectRepository.findByProjectId(1L)).thenReturn(project);

        ResponseEntity<ProjectDetails> response = employeeProjectService.getEmployeeProjects(employeeEmail);
        ProjectDetails projectDetails = response.getBody();

        assertEquals(employeeEmail, projectDetails.getEmployeeEmail());
        assertEquals(1L, projectDetails.getProjectId());
        assertEquals("manager@example.com", projectDetails.getManagerEmail());
        assertEquals("Project Name", projectDetails.getProjectName());
        assertEquals("Project Description", projectDetails.getProjectDesc());
    }


}
