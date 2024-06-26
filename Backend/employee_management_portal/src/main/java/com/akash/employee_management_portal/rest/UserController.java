package com.akash.employee_management_portal.rest;

import com.akash.employee_management_portal.dto.*;
import com.akash.employee_management_portal.entity.User;
import com.akash.employee_management_portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        return userService.findByEmailAndPassword(email,password);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        return userService.registerUser(registrationRequest);
    }

    @PutMapping("/user/update")
    public ResponseEntity<String> updateUser(@RequestBody RegistrationRequest updateRequest){
        return userService.updateUser(updateRequest);
    }

    @DeleteMapping("/delete/{email}")
    public void deleteEmployee(@PathVariable("email") String email) {
        userService.deleteEmployeeByEmail(email);
    }

    @GetMapping("/employee")
    public List<User> getAllEmployees() {
        return userService.findByType("EMPLOYEE");
    }

    @GetMapping("/user/{email}")
    public User findByEmail(@PathVariable("email") String userEmail){
        return userService.findByEmail(userEmail);
    }

    @GetMapping("/manager")
    public List<User> getAllManagers() {
        return userService.findByType("MANAGER");
    }

    @GetMapping("/admin")
    public List<User> getAllAdmin() {
        return userService.findByType("ADMIN");
    }

    @GetMapping("/all")
    public List<User> getAll() {
        return userService.findAll();
    }

    @GetMapping("/employeeskills")
    public List<EmployeeSkillDTO> getEmployeeSkills() {
        return userService.findEmployeeSkills();
    }

    @GetMapping("/employeeskills/{skill}")
    public List<FilterEmployeeDTO> filterEmployeeWithSkill(@PathVariable("skill") String skill) {
        return userService.filterEmployeeWithSkills(skill);
    }

    @GetMapping("/employeesproject")
    List<EmployeeProjectDTO> getEmployeesAndProjects(){
        return userService.getEmployeesAndProjects();
    }

    @GetMapping("/managersproject")
    List<ManagerProjectDTO> getManagersAndProjects() {
        return userService.getManagersAndProjects();
    };

    @GetMapping("/employeeandskills/{email}")
    List<EmployeeSkillDTO> findEmployeeAndSkills(@PathVariable("email") String email){
        return userService.findEmployeeAndSkills(email);
    }

}
