package com.akash.employee_management_portal.rest;

import com.akash.employee_management_portal.dto.ResourceRequestDTO;
import com.akash.employee_management_portal.entity.ResourceRequest;
import com.akash.employee_management_portal.service.ResourceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("requests/all")
    public List<ResourceRequest> getRequests() {
        return resourceRequestService.getRequests();
    }

    @PutMapping("request/approve/{requestId}")
    public ResponseEntity<String> approveRequest(@PathVariable("requestId") long requestId) {
        return resourceRequestService.approveRequest(requestId);
    }

    @PutMapping("request/reject/{requestId}")
    public ResponseEntity<String> rejectRequest(@PathVariable("requestId") long requestId) {
        return resourceRequestService.rejectRequest(requestId);
    }

    @DeleteMapping("request/delete/{requestId}")
    public ResponseEntity<String> deleteRequest(@PathVariable("requestId") long requestId) {
        return resourceRequestService.deleteRequest(requestId);
    }

}
