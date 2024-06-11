package com.akash.employee_management_portal.rest;

import com.akash.employee_management_portal.dto.ResourceRequestDTO;
import com.akash.employee_management_portal.entity.ResourceRequest;
import com.akash.employee_management_portal.service.ResourceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("resource")
public class ResourceRequestController {

    @Autowired
    private ResourceRequestService resourceRequestService;

    @PostMapping("request")
    public ResponseEntity<String> requestResource(@RequestBody ResourceRequestDTO resourceRequestDTO){
        resourceRequestService.requestResource(resourceRequestDTO);
        return ResponseEntity.ok("Request created");
    }
}
