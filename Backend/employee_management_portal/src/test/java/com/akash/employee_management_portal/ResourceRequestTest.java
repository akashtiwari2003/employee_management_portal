package com.akash.employee_management_portal;

import com.akash.employee_management_portal.entity.ResourceRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ResourceRequestTest {

    private ResourceRequest resourceRequest;

    @BeforeEach
    void setUp() {
        resourceRequest = new ResourceRequest();
    }

    @Test
    void testDefaultConstructor() {
        assertNull(resourceRequest.getRequest());
        assertNull(resourceRequest.getManagerEmail());
        assertNull(resourceRequest.getStatus());
        assertEquals(0, resourceRequest.getRequestId());
        assertEquals(0, resourceRequest.getProjectId());
    }

    @Test
    void testParameterizedConstructor() {
        ResourceRequest parameterizedRequest = new ResourceRequest("New Hire", "manager@example.com", 12345, "Pending");
        assertEquals("New Hire", parameterizedRequest.getRequest());
        assertEquals("manager@example.com", parameterizedRequest.getManagerEmail());
        assertEquals(12345, parameterizedRequest.getProjectId());
        assertEquals("Pending", parameterizedRequest.getStatus());
    }

    @Test
    void testGetRequestId() {
        resourceRequest.setRequestId(1);
        assertEquals(1, resourceRequest.getRequestId());
    }

    @Test
    void testSetRequestId() {
        resourceRequest.setRequestId(1);
        assertEquals(1, resourceRequest.getRequestId());
    }

    @Test
    void testGetRequest() {
        resourceRequest.setRequest("New Hire");
        assertEquals("New Hire", resourceRequest.getRequest());
    }

    @Test
    void testSetRequest() {
        resourceRequest.setRequest("New Hire");
        assertEquals("New Hire", resourceRequest.getRequest());
    }

    @Test
    void testGetManagerEmail() {
        resourceRequest.setManagerEmail("manager@example.com");
        assertEquals("manager@example.com", resourceRequest.getManagerEmail());
    }

    @Test
    void testSetManagerEmail() {
        resourceRequest.setManagerEmail("manager@example.com");
        assertEquals("manager@example.com", resourceRequest.getManagerEmail());
    }

    @Test
    void testGetProjectId() {
        resourceRequest.setProjectId(12345);
        assertEquals(12345, resourceRequest.getProjectId());
    }

    @Test
    void testSetProjectId() {
        resourceRequest.setProjectId(12345);
        assertEquals(12345, resourceRequest.getProjectId());
    }

    @Test
    void testGetStatus() {
        resourceRequest.setStatus("Pending");
        assertEquals("Pending", resourceRequest.getStatus());
    }

    @Test
    void testSetStatus() {
        resourceRequest.setStatus("Pending");
        assertEquals("Pending", resourceRequest.getStatus());
    }
}
