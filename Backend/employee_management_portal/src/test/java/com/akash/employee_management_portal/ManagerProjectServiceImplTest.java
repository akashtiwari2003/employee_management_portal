package com.akash.employee_management_portal;

import com.akash.employee_management_portal.dto.AvailableProjectDTO;
import com.akash.employee_management_portal.dto.ManagerProjectDTO;
import com.akash.employee_management_portal.entity.ManagerProject;
import com.akash.employee_management_portal.repository.ManagerProjectRepository;
import com.akash.employee_management_portal.service.ManagerProjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ManagerProjectServiceImplTest {

    @Mock
    private ManagerProjectRepository managerProjectRepository;

    @InjectMocks
    private ManagerProjectServiceImpl managerProjectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAssignManager() {
        ManagerProjectDTO managerProjectDTO = new ManagerProjectDTO("manager@example.com", 1L);
        ManagerProject managerProject = new ManagerProject("manager@example.com", 1L);

        when(managerProjectRepository.save(any(ManagerProject.class))).thenReturn(managerProject);

        ResponseEntity<String> response = managerProjectService.assignManager(managerProjectDTO);

        assertEquals("Manager Assigned Project", response.getBody());
        verify(managerProjectRepository, times(1)).save(any(ManagerProject.class));
    }

    @Test
    void testUnassignManager() {
        String managerEmail = "manager@example.com";
        ManagerProject managerProject = new ManagerProject("manager@example.com", 1L);

        when(managerProjectRepository.findByManagerEmail(managerEmail)).thenReturn(managerProject);
        doNothing().when(managerProjectRepository).delete(managerProject);

        ResponseEntity<String> response = managerProjectService.unassignManager(managerEmail);

        assertEquals("Project Unassigned from Manager", response.getBody());
        verify(managerProjectRepository, times(1)).findByManagerEmail(managerEmail);
        verify(managerProjectRepository, times(1)).delete(managerProject);
    }

    @Test
    void testFindProjectsNotManaged() {
        List<AvailableProjectDTO> availableProjects = List.of(
                new AvailableProjectDTO(1L, "Project 1", "Description 1"),
                new AvailableProjectDTO(2L, "Project 2", "Description 2")
        );

        when(managerProjectRepository.findProjectsNotManaged()).thenReturn(availableProjects);

        List<AvailableProjectDTO> result = managerProjectService.findProjectsNotManaged();

        assertEquals(availableProjects.size(), result.size());
        assertEquals(availableProjects.get(0).getProjectId(), result.get(0).getProjectId());
        assertEquals(availableProjects.get(0).getProjectName(), result.get(0).getProjectName());
        assertEquals(availableProjects.get(0).getProjectDesc(), result.get(0).getProjectDesc());
        verify(managerProjectRepository, times(1)).findProjectsNotManaged();
    }
}
