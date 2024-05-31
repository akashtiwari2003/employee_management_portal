package com.akash.employee_management_portal.rest;

import com.akash.employee_management_portal.dto.LoginRequest;
import com.akash.employee_management_portal.dto.RegistrationRequest;
import com.akash.employee_management_portal.entity.User;
import com.akash.employee_management_portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


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

    @DeleteMapping("/delete/{email}")
    public void deleteEmployee(@PathVariable("email") String email) {
        userService.deleteEmployeeByEmail(email );
    }
}
