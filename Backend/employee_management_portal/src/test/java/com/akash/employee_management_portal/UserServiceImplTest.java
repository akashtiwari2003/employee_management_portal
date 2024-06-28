package com.akash.employee_management_portal;

import com.akash.employee_management_portal.dto.*;
import com.akash.employee_management_portal.entity.User;
import com.akash.employee_management_portal.repository.UserRepository;
import com.akash.employee_management_portal.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByEmail() {
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(user);

        User result = userService.findByEmail(email);

        assertEquals(email, result.getEmail());
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void testFindByEmailAndPassword() {
        String email = "test@example.com";
        String password = "password";
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        when(userRepository.findByEmailAndPassword(email, password)).thenReturn(user);

        ResponseEntity<User> response = userService.findByEmailAndPassword(email, password);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testRegisterUser() {
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setEmail("test@example.com");
        registrationRequest.setFirstName("Test");
        registrationRequest.setLastName("User");
        registrationRequest.setPassword("password");
        registrationRequest.setType("EMPLOYEE");

        User user = new User(
                registrationRequest.getEmail(),
                registrationRequest.getFirstName(),
                registrationRequest.getLastName(),
                registrationRequest.getPassword(),
                registrationRequest.getType()
        );

        when(userRepository.save(any(User.class))).thenReturn(user);

        ResponseEntity<String> response = userService.registerUser(registrationRequest);

        assertEquals("User Registered Successfully", response.getBody());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testUpdateUser() {
        RegistrationRequest updateRequest = new RegistrationRequest();
        updateRequest.setEmail("test@example.com");
        updateRequest.setFirstName("Updated");
        updateRequest.setLastName("User");
        updateRequest.setPassword("newpassword");

        User user = new User();
        user.setEmail(updateRequest.getEmail());

        when(userRepository.findByEmail(updateRequest.getEmail())).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);

        ResponseEntity<String> response = userService.updateUser(updateRequest);

        assertEquals("User Details Updated", response.getBody());
        verify(userRepository, times(1)).findByEmail(updateRequest.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testDeleteEmployeeByEmail() {
        String email = "test@example.com";

        doNothing().when(userRepository).deleteById(email);

        userService.deleteEmployeeByEmail(email);

        verify(userRepository, times(1)).deleteById(email);
    }

    @Test
    void testFindByType() {
        String type = "EMPLOYEE";
        User user1 = new User();
        user1.setType(type);
        User user2 = new User();
        user2.setType(type);

        when(userRepository.findByType(type)).thenReturn(Arrays.asList(user1, user2));

        List<User> result = userService.findByType(type);

        assertEquals(2, result.size());
        assertEquals(type, result.get(0).getType());
        assertEquals(type, result.get(1).getType());
    }

    @Test
    void testFindAll() {
        User user1 = new User();
        User user2 = new User();

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> result = userService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void testFindEmployeeSkills() {
        EmployeeSkillDTO skillDTO = new EmployeeSkillDTO("Test", "Java");
        when(userRepository.findEmployeeSkills()).thenReturn(List.of(skillDTO));

        List<EmployeeSkillDTO> result = userService.findEmployeeSkills();

        assertEquals(1, result.size());
        assertEquals("Test", result.get(0).getFirstName());
        assertEquals("Java", result.get(0).getSkillName());
    }

    @Test
    void testFilterEmployeeWithSkills() {
        String skill = "Java";
        FilterEmployeeDTO skillDTO = new FilterEmployeeDTO("Test", 1, skill);

        when(userRepository.filterEmployeeWithSkills(skill)).thenReturn(List.of(skillDTO));

        List<FilterEmployeeDTO> result = userService.filterEmployeeWithSkills(skill);

        assertEquals(1, result.size());
        assertEquals("Test", result.get(0).getName());
        assertEquals(skill, result.get(0).getSkillName());
    }

    @Test
    void testGetEmployeesAndProjects() {
        EmployeeProjectDTO projectDTO = new EmployeeProjectDTO("test@example.com", 1L);

        when(userRepository.getEmployeesAndProjects()).thenReturn(Arrays.asList(projectDTO));

        List<EmployeeProjectDTO> result = userService.getEmployeesAndProjects();

        assertEquals(1, result.size());
        assertEquals("test@example.com", result.get(0).getEmployeeEmail());
        assertEquals(1L, result.get(0).getProjectId());
    }

    @Test
    void testGetManagersAndProjects() {
        ManagerProjectDTO projectDTO = new ManagerProjectDTO("manager@example.com", 1L);

        when(userRepository.getManagersAndProjects()).thenReturn(Arrays.asList(projectDTO));

        List<ManagerProjectDTO> result = userService.getManagersAndProjects();

        assertEquals(1, result.size());
        assertEquals("manager@example.com", result.get(0).getManagerEmail());
        assertEquals(1L, result.get(0).getProjectId());
    }

    @Test
    void testFindEmployeeAndSkills() {
        String email = "test@example.com";
        EmployeeSkillDTO skillDTO = new EmployeeSkillDTO("Test", "Java");

        when(userRepository.findEmployeeAndSkills(email)).thenReturn(List.of(skillDTO));

        List<EmployeeSkillDTO> result = userService.findEmployeeAndSkills(email);

        assertEquals(1, result.size());
        assertEquals("Test", result.get(0).getFirstName());
        assertEquals("Java", result.get(0).getSkillName());
    }
}
