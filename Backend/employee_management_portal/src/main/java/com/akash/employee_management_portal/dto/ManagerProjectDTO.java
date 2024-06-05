package com.akash.employee_management_portal.dto;

public class ManagerProjectDTO {
    private String managerEmail;
    private long projectId;

    public ManagerProjectDTO(String managerEmail, long projectId) {
        this.managerEmail = managerEmail;
        this.projectId = projectId;
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

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
