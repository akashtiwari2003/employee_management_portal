package com.akash.employee_management_portal;

import com.akash.employee_management_portal.dto.ResourceRequestDTO;
import com.akash.employee_management_portal.entity.ResourceRequest;
import com.akash.employee_management_portal.repository.ResourceRequestRepository;
import com.akash.employee_management_portal.service.ResourceRequestServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ResourceRequestServiceImplTest {

    @InjectMocks
    private ResourceRequestServiceImpl resourceRequestService;

    @Mock
    private ResourceRequestRepository resourceRequestRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRequestResource() {
        ResourceRequestDTO resourceRequestDTO = new ResourceRequestDTO();
        resourceRequestDTO.setRequest("Resource needed");
        resourceRequestDTO.setManagerEmail("manager@example.com");
        resourceRequestDTO.setProjectId(1L);
        resourceRequestDTO.setStatus("PENDING");

        ResourceRequest request = new ResourceRequest("Resource needed", "manager@example.com", 1L, "PENDING");

        when(resourceRequestRepository.save(any(ResourceRequest.class))).thenReturn(request);

        ResponseEntity<String> response = resourceRequestService.requestResource(resourceRequestDTO);
        assertEquals("Request created", response.getBody());
    }

    @Test
    void testGetRequests() {
        ResourceRequest request1 = new ResourceRequest("Resource 1", "manager1@example.com", 1L, "PENDING");
        ResourceRequest request2 = new ResourceRequest("Resource 2", "manager2@example.com", 2L, "APPROVED");
        List<ResourceRequest> requests = Arrays.asList(request1, request2);

        when(resourceRequestRepository.findAll()).thenReturn(requests);

        List<ResourceRequest> result = resourceRequestService.getRequests();
        assertEquals(2, result.size());
        assertEquals("Resource 1", result.get(0).getRequest());
        assertEquals("Resource 2", result.get(1).getRequest());
    }

    @Test
    void testApproveRequest() {
        ResourceRequest request = new ResourceRequest("Resource needed", "manager@example.com", 1L, "PENDING");
        request.setRequestId(1L);

        when(resourceRequestRepository.findById(anyLong())).thenReturn(request);
        when(resourceRequestRepository.save(any(ResourceRequest.class))).thenReturn(request);

        ResponseEntity<String> response = resourceRequestService.approveRequest(1L);
        assertEquals("Request Approved", response.getBody());
        assertEquals("APPROVED", request.getStatus());
    }

    @Test
    void testRejectRequest() {
        ResourceRequest request = new ResourceRequest("Resource needed", "manager@example.com", 1L, "PENDING");
        request.setRequestId(1L);

        when(resourceRequestRepository.findById(anyLong())).thenReturn(request);
        when(resourceRequestRepository.save(any(ResourceRequest.class))).thenReturn(request);

        ResponseEntity<String> response = resourceRequestService.rejectRequest(1L);
        assertEquals("Request Rejected", response.getBody());
        assertEquals("REJECTED", request.getStatus());
    }

    @Test
    void testDeleteRequest() {
        ResourceRequest request = new ResourceRequest("Resource needed", "manager@example.com", 1L, "PENDING");
        request.setRequestId(1L);

        doNothing().when(resourceRequestRepository).deleteById(anyLong());

        ResponseEntity<String> response = resourceRequestService.deleteRequest(1L);
        assertEquals("Request Deleted", response.getBody());

        verify(resourceRequestRepository, times(1)).deleteById(1L);
    }
}
