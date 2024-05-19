package com.akash.employee_management_portal.service;

import com.akash.employee_management_portal.dto.RegistrationRequest;
import com.akash.employee_management_portal.entity.User;
import com.akash.employee_management_portal.entity.Worker;
import com.akash.employee_management_portal.repository.UserRepository;
import com.akash.employee_management_portal.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkerRepository workerRepository;



//    @Autowired
//    public UserServiceImpl(WorkerRepository workerRepository) {
//        this.workerRepository = workerRepository;
//    }

    //    @Autowired
//    public UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }


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
        Worker worker = new Worker(registrationRequest.getEmail(),
                registrationRequest.getFirstName(),
                registrationRequest.getLastName(),
                registrationRequest.getPassword(),
                registrationRequest.getType());

        workerRepository.save(worker);
        return ResponseEntity.ok("User Registered Successfully");
    }

}
