package com.akash.employee_management_portal.service;

import com.akash.employee_management_portal.dto.ResourceRequestDTO;
import com.akash.employee_management_portal.entity.ResourceRequest;
import com.akash.employee_management_portal.repository.ResourceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResourceRequestServiceImpl implements ResourceRequestService{

    @Autowired
    private ResourceRequestRepository resourceRequestRepository;

    @Override
    public ResponseEntity<String> requestResource(ResourceRequestDTO resourceRequestDTO) {
        ResourceRequest request = new ResourceRequest(resourceRequestDTO.getRequest(), resourceRequestDTO.getManagerEmail(),resourceRequestDTO.getProjectId(),resourceRequestDTO.getStatus());
        resourceRequestRepository.save(request);
        return ResponseEntity.ok("Request created");
    }
}
