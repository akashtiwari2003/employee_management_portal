package com.akash.employee_management_portal.repository;

import com.akash.employee_management_portal.dto.ResourceRequestDTO;
import com.akash.employee_management_portal.entity.ResourceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceRequestRepository extends JpaRepository<ResourceRequest, Long> {
    List<ResourceRequest> findAll();
    ResourceRequest findById(long requestId);
    void deleteById(long requestId);
}
