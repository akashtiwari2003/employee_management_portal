package com.akash.employee_management_portal.repository;

import com.akash.employee_management_portal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmailAndPassword(String email, String password);
    List<User> findByType(String type);
    List<User> findAll();
}
