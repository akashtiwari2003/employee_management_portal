package com.akash.employee_management_portal.repository;

import com.akash.employee_management_portal.entity.ResourceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRequestRepository extends JpaRepository<ResourceRequest, Long> {
}
