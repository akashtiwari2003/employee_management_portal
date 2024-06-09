package com.akash.employee_management_portal;

import com.akash.employee_management_portal.dto.EmployeeProjectDTO;
import com.akash.employee_management_portal.dto.ProjectDetails;
import com.akash.employee_management_portal.rest.EmployeeController;
import com.akash.employee_management_portal.service.EmployeeProjectService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class EmployeeControllerTest {

    @Mock
    private EmployeeProjectService employeeProjectService;

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;

    public EmployeeControllerTest() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    void testAssignEmployee() throws Exception {
        EmployeeProjectDTO employeeProjectDTO = new EmployeeProjectDTO("test@example.com", 1L);

        when(employeeProjectService.assignEmployee(any(EmployeeProjectDTO.class))).thenReturn(ResponseEntity.ok("Project Assigned to Employee"));

        mockMvc.perform(post("/employee/assignemployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"employeeEmail\":\"test@example.com\",\"projectId\":1}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Project Assigned to Employee"));
    }

    @Test
    void testUnassignEmployee() throws Exception {
        String employeeEmail = "test@example.com";

        when(employeeProjectService.unassignEmployee(employeeEmail)).thenReturn(ResponseEntity.ok("Project Unassigned from Employee"));

        mockMvc.perform(delete("/employee/unassign/{employeeEmail}", employeeEmail))
                .andExpect(status().isOk())
                .andExpect(content().string("Project Unassigned from Employee"));
    }

    @Test
    void testGetEmployeeProject() throws Exception {
        String employeeEmail = "test@example.com";
        ProjectDetails projectDetails = new ProjectDetails(employeeEmail, 1L, "manager@example.com", "Project Name", "Project Description");

        when(employeeProjectService.getEmployeeProjects(employeeEmail)).thenReturn(ResponseEntity.ok(projectDetails));

        mockMvc.perform(get("/employee/employeeproject/{employeeEmail}", employeeEmail))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeEmail").value(employeeEmail))
                .andExpect(jsonPath("$.projectId").value(1L))
                .andExpect(jsonPath("$.managerEmail").value("manager@example.com"))
                .andExpect(jsonPath("$.projectName").value("Project Name"))
                .andExpect(jsonPath("$.projectDesc").value("Project Description"));
    }
}
