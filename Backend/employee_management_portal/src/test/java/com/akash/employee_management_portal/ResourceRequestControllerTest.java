package com.akash.employee_management_portal;

import com.akash.employee_management_portal.dto.ResourceRequestDTO;
import com.akash.employee_management_portal.entity.ResourceRequest;
import com.akash.employee_management_portal.rest.ResourceRequestController;
import com.akash.employee_management_portal.service.ResourceRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ResourceRequestControllerTest {

    @InjectMocks
    private ResourceRequestController resourceRequestController;

    @Mock
    private ResourceRequestService resourceRequestService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRequestResource() {
        ResourceRequestDTO requestDTO = new ResourceRequestDTO();
        requestDTO.setRequest("Resource needed");
        requestDTO.setManagerEmail("manager@example.com");
        requestDTO.setProjectId(1L);
        requestDTO.setStatus("PENDING");

        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Request created");

        doReturn(expectedResponse).when(resourceRequestService).requestResource(any(ResourceRequestDTO.class));

        ResponseEntity<String> response = resourceRequestController.requestResource(requestDTO);
        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());

        verify(resourceRequestService, times(1)).requestResource(any(ResourceRequestDTO.class));
    }

    @Test
    void testGetRequests() {
        ResourceRequest request1 = new ResourceRequest("Resource 1", "manager1@example.com", 1L, "PENDING");
        ResourceRequest request2 = new ResourceRequest("Resource 2", "manager2@example.com", 2L, "APPROVED");
        List<ResourceRequest> requests = Arrays.asList(request1, request2);

        when(resourceRequestService.getRequests()).thenReturn(requests);

        List<ResourceRequest> response = resourceRequestController.getRequests();
        assertEquals(2, response.size());
        assertEquals("Resource 1", response.get(0).getRequest());
        assertEquals("Resource 2", response.get(1).getRequest());

        verify(resourceRequestService, times(1)).getRequests();
    }

    @Test
    void testApproveRequest() {
        long requestId = 1L;
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Request Approved");

        doReturn(expectedResponse).when(resourceRequestService).approveRequest(anyLong());

        ResponseEntity<String> response = resourceRequestController.approveRequest(requestId);
        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());

        verify(resourceRequestService, times(1)).approveRequest(requestId);
    }

    @Test
    void testRejectRequest() {
        long requestId = 1L;
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Request Rejected");

        doReturn(expectedResponse).when(resourceRequestService).rejectRequest(anyLong());

        ResponseEntity<String> response = resourceRequestController.rejectRequest(requestId);
        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());

        verify(resourceRequestService, times(1)).rejectRequest(requestId);
    }

    @Test
    void testDeleteRequest() {
        long requestId = 1L;
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Request Deleted");

        doReturn(expectedResponse).when(resourceRequestService).deleteRequest(anyLong());

        ResponseEntity<String> response = resourceRequestController.deleteRequest(requestId);
        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());

        verify(resourceRequestService, times(1)).deleteRequest(requestId);
    }
}
