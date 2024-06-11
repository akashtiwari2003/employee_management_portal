package com.akash.employee_management_portal.dto;

public class ResourceRequestDTO {
    private String request;
    private String managerEmail;
    private long projectId;
    private String status;

    public ResourceRequestDTO() {
    }

    public ResourceRequestDTO(String request, String managerEmail, long projectId, String status) {
        this.request = request;
        this.managerEmail = managerEmail;
        this.projectId = projectId;
        this.status = status;
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
