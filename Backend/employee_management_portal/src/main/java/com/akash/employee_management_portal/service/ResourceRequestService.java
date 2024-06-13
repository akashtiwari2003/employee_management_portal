package com.akash.employee_management_portal.service;

import com.akash.employee_management_portal.dto.ResourceRequestDTO;
import com.akash.employee_management_portal.entity.ResourceRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ResourceRequestService {
    ResponseEntity<String> requestResource(ResourceRequestDTO resourceRequestDTO);
    List<ResourceRequest> getRequests();
    ResponseEntity<String> approveRequest(long requestId);
    ResponseEntity<String> rejectRequest(long requestId);
    ResponseEntity<String> deleteRequest(long requestId);
}
