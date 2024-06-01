package com.akash.employee_management_portal.service;

import com.akash.employee_management_portal.dto.RegistrationRequest;
import com.akash.employee_management_portal.dto.UpdateRequest;
import com.akash.employee_management_portal.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    User findById(String theId);
    ResponseEntity<User> findByEmailAndPassword(String email, String password);
    ResponseEntity<String> registerUser(RegistrationRequest registrationRequest);
    void deleteEmployeeByEmail(String email);
    List<User> findByType(String type);
    List<User> findAll();
}
