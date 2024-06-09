package com.akash.employee_management_portal;

import com.akash.employee_management_portal.dto.AvailableProjectDTO;
import com.akash.employee_management_portal.dto.ManagerProjectDTO;
import com.akash.employee_management_portal.rest.ManagerController;
import com.akash.employee_management_portal.service.ManagerProjectService;
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

class ManagerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ManagerProjectService managerProjectService;

    @InjectMocks
    private ManagerController managerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(managerController).build();
    }

    @Test
    void testAssignManager() throws Exception {
        ManagerProjectDTO managerProjectDTO = new ManagerProjectDTO("manager@example.com", 1L);

        when(managerProjectService.assignManager(any(ManagerProjectDTO.class))).thenReturn(ResponseEntity.ok("Manager Assigned Project"));

        mockMvc.perform(post("/assignmanager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"managerEmail\":\"manager@example.com\", \"projectId\":1}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Manager Assigned Project"));

        verify(managerProjectService, times(1)).assignManager(any(ManagerProjectDTO.class));
    }

    @Test
    void testUnassignManager() throws Exception {
        String managerEmail = "manager@example.com";

        when(managerProjectService.unassignManager(managerEmail)).thenReturn(ResponseEntity.ok("Project Unassigned from Manager"));

        mockMvc.perform(delete("/unassign/{managerEmail}", managerEmail))
                .andExpect(status().isOk())
                .andExpect(content().string("Project Unassigned from Manager"));

        verify(managerProjectService, times(1)).unassignManager(managerEmail);
    }

    @Test
    void testFindProjectsNotManaged() throws Exception {
        List<AvailableProjectDTO> availableProjects = List.of(
                new AvailableProjectDTO(1L, "Project 1", "Description 1"),
                new AvailableProjectDTO(2L, "Project 2", "Description 2")
        );

        when(managerProjectService.findProjectsNotManaged()).thenReturn(availableProjects);

        mockMvc.perform(get("/assignmanagerprojects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].projectId").value(1))
                .andExpect(jsonPath("$[0].projectName").value("Project 1"))
                .andExpect(jsonPath("$[0].projectDesc").value("Description 1"))
                .andExpect(jsonPath("$[1].projectId").value(2))
                .andExpect(jsonPath("$[1].projectName").value("Project 2"))
                .andExpect(jsonPath("$[1].projectDesc").value("Description 2"));

        verify(managerProjectService, times(1)).findProjectsNotManaged();
    }
}
