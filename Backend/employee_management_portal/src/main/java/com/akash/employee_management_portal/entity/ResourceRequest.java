package com.akash.employee_management_portal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "resource_request")
public class ResourceRequest {
    @Id
    @Column(name = "request_id")
    private long requestId;

    @Column(name = "request")
    private String request;

    @Column(name = "manager_email")
    private String managerEmail;

    @Column(name = "project_id")
    private long projectId;

    @Column(name = "status")
    private String status;

    public ResourceRequest() {
    }

    public ResourceRequest(String request, String managerEmail, long projectId, String status) {
        this.request = request;
        this.managerEmail = managerEmail;
        this.projectId = projectId;
        this.status = status;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
