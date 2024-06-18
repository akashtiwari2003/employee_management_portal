package com.akash.employee_management_portal;

import com.akash.employee_management_portal.dto.*;
import com.akash.employee_management_portal.entity.User;
import com.akash.employee_management_portal.rest.UserController;
import com.akash.employee_management_portal.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testLogin() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password");

        User user = new User();
        user.setEmail("test@example.com");

        when(userService.findByEmailAndPassword("test@example.com", "password")).thenReturn(ResponseEntity.ok(user));

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@example.com"));

        verify(userService, times(1)).findByEmailAndPassword("test@example.com", "password");
    }

    @Test
    void testRegisterUser() throws Exception {
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setEmail("test@example.com");
        registrationRequest.setFirstName("Test");
        registrationRequest.setLastName("User");
        registrationRequest.setPassword("password");
        registrationRequest.setType("EMPLOYEE");

        when(userService.registerUser(any(RegistrationRequest.class))).thenReturn(ResponseEntity.ok("User Registered Successfully"));

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\",\"firstName\":\"Test\",\"lastName\":\"User\",\"password\":\"password\",\"type\":\"EMPLOYEE\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("User Registered Successfully"));

        verify(userService, times(1)).registerUser(any(RegistrationRequest.class));
    }

    @Test
    void testUpdateUser() throws Exception {
        RegistrationRequest updateRequest = new RegistrationRequest();
        updateRequest.setEmail("test@example.com");
        updateRequest.setFirstName("Updated");
        updateRequest.setLastName("User");
        updateRequest.setPassword("newpassword");

        when(userService.updateUser(any(RegistrationRequest.class))).thenReturn(ResponseEntity.ok("User Details Updated"));

        mockMvc.perform(put("/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\",\"firstName\":\"Updated\",\"lastName\":\"User\",\"password\":\"newpassword\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("User Details Updated"));

        verify(userService, times(1)).updateUser(any(RegistrationRequest.class));
    }

    @Test
    void testDeleteEmployee() throws Exception {
        String email = "test@example.com";

        doNothing().when(userService).deleteEmployeeByEmail(email);

        mockMvc.perform(delete("/delete/{email}", email))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteEmployeeByEmail(email);
    }

    @Test
    void testGetAllEmployees() throws Exception {
        User user1 = new User();
        user1.setType("EMPLOYEE");
        User user2 = new User();
        user2.setType("EMPLOYEE");

        when(userService.findByType("EMPLOYEE")).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/employee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].type").value("EMPLOYEE"))
                .andExpect(jsonPath("$[1].type").value("EMPLOYEE"));

        verify(userService, times(1)).findByType("EMPLOYEE");
    }

    @Test
    void testFindByEmail() throws Exception {
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);

        when(userService.findByEmail(email)).thenReturn(user);

        mockMvc.perform(get("/user/{email}", email))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(email));

        verify(userService, times(1)).findByEmail(email);
    }

    @Test
    void testGetAllManagers() throws Exception {
        User user1 = new User();
        user1.setType("MANAGER");
        User user2 = new User();
        user2.setType("MANAGER");

        when(userService.findByType("MANAGER")).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/manager"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].type").value("MANAGER"))
                .andExpect(jsonPath("$[1].type").value("MANAGER"));

        verify(userService, times(1)).findByType("MANAGER");
    }

    @Test
    void testGetAllAdmin() throws Exception {
        User user1 = new User();
        user1.setType("ADMIN");
        User user2 = new User();
        user2.setType("ADMIN");

        when(userService.findByType("ADMIN")).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].type").value("ADMIN"))
                .andExpect(jsonPath("$[1].type").value("ADMIN"));

        verify(userService, times(1)).findByType("ADMIN");
    }

    @Test
    void testGetAll() throws Exception {
        User user1 = new User();
        User user2 = new User();

        when(userService.findAll()).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value(user1.getEmail()))
                .andExpect(jsonPath("$[1].email").value(user2.getEmail()));

        verify(userService, times(1)).findAll();
    }

    @Test
    void testGetEmployeeSkills() throws Exception {
        EmployeeSkillDTO skillDTO = new EmployeeSkillDTO("Test", "Java");

        when(userService.findEmployeeSkills()).thenReturn(Arrays.asList(skillDTO));

        mockMvc.perform(get("/employeeskills"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Test"))
                .andExpect(jsonPath("$[0].skillName").value("Java"));

        verify(userService, times(1)).findEmployeeSkills();
    }

    @Test
    void testFilterEmployeeWithSkill() throws Exception {
        String skill = "Java";
        EmployeeSkillDTO skillDTO = new EmployeeSkillDTO("Test", skill);

        FilterEmployeeDTO FilterEmployeeDTO = new FilterEmployeeDTO("Akash Tiwari", 1L,"Java");
        when(userService.filterEmployeeWithSkills(skill)).thenReturn(Arrays.asList(FilterEmployeeDTO));

        mockMvc.perform(get("/employeeskills/{skill}", skill))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Test"))
                .andExpect(jsonPath("$[0].skillName").value(skill));

        verify(userService, times(1)).filterEmployeeWithSkills(skill);
    }

    @Test
    void testGetEmployeesAndProjects() throws Exception {
        EmployeeProjectDTO projectDTO = new EmployeeProjectDTO("test@example.com", 1L);

        when(userService.getEmployeesAndProjects()).thenReturn(Arrays.asList(projectDTO));

        mockMvc.perform(get("/employeesproject"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].employeeEmail").value("test@example.com"))
                .andExpect(jsonPath("$[0].projectId").value(1L));

        verify(userService, times(1)).getEmployeesAndProjects();
    }

    @Test
    void testGetManagersAndProjects() throws Exception {
        ManagerProjectDTO projectDTO = new ManagerProjectDTO("manager@example.com", 1L);

        when(userService.getManagersAndProjects()).thenReturn(Arrays.asList(projectDTO));

        mockMvc.perform(get("/managersproject"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].managerEmail").value("manager@example.com"))
                .andExpect(jsonPath("$[0].projectId").value(1L));

        verify(userService, times(1)).getManagersAndProjects();
    }

    @Test
    void testFindEmployeeAndSkills() throws Exception {
        String email = "test@example.com";
        EmployeeSkillDTO skillDTO = new EmployeeSkillDTO("Test", "Java");

        when(userService.findEmployeeAndSkills(email)).thenReturn(Arrays.asList(skillDTO));

        mockMvc.perform(get("/employeeandskills/{email}", email))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Test"))
                .andExpect(jsonPath("$[0].skillName").value("Java"));

        verify(userService, times(1)).findEmployeeAndSkills(email);
    }
}