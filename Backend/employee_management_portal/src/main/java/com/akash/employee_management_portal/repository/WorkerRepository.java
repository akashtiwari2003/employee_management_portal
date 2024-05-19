package com.akash.employee_management_portal.repository;

import com.akash.employee_management_portal.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, String> {
    Worker findByEmail(String email);
}
