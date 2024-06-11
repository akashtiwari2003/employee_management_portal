package com.akash.employee_management_portal.service;

import com.akash.employee_management_portal.dto.ResourceRequestDTO;
import org.springframework.http.ResponseEntity;

public interface ResourceRequestService {
    ResponseEntity<String> requestResource(ResourceRequestDTO resourceRequestDTO);
}
