package com.akash.employee_management_portal.service;

import com.akash.employee_management_portal.dto.EmployeeProjectDTO;
import com.akash.employee_management_portal.dto.EmployeeSkillDTO;
import com.akash.employee_management_portal.dto.RegistrationRequest;
import com.akash.employee_management_portal.dto.UpdateRequest;
import com.akash.employee_management_portal.entity.User;
import com.akash.employee_management_portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(String theId) {
        return null;
    }

    @Override
    public ResponseEntity<User> findByEmailAndPassword(String email, String password) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmailAndPassword(email, password));

        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userRepository.findByEmailAndPassword(email, password));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @Override
    public ResponseEntity<String> registerUser(RegistrationRequest registrationRequest) {
//        Worker worker = new Worker(registrationRequest.getEmail(),
//                registrationRequest.getFirstName(),
//                registrationRequest.getLastName(),
//                registrationRequest.getPassword(),
//                registrationRequest.getType());
//        workerRepository.save(worker);
//
//        User user = new User(registrationRequest.getEmail(),
//                registrationRequest.getPassword(),
//                registrationRequest.getType());
//        userRepository.save(user);
        User user = new User(
                registrationRequest.getEmail(),
                registrationRequest.getFirstName(),
                registrationRequest.getLastName(),
                registrationRequest.getPassword(),
                registrationRequest.getType()
        );
        userRepository.save(user);
return ResponseEntity.ok("User Registered Successfully");
    }

    @Override
    public void deleteEmployeeByEmail(String email) {
        userRepository.deleteById(email);
    }

    @Override
    public List<User> findByType(String type) {
        return userRepository.findByType(type);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<EmployeeSkillDTO> findEmployeeSkills() {
        return userRepository.findEmployeeSkills();
    }

    @Override
    public List<EmployeeSkillDTO> filterEmployeeWithSkills(String skill) {
        return userRepository.filterEmployeeWithSkills(skill);
    }

    @Override
    public List<EmployeeProjectDTO> getEmployeesAndProjects() {
        return userRepository.getEmployeesAndProjects();
    }


}
