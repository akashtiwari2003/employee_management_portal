package com.akash.employee_management_portal.dto;

public class EmployeeProjectDTO {
    private String employeeEmail;
    private long projectId;

    public EmployeeProjectDTO(String employeeEmail, long projectId) {
        this.employeeEmail = employeeEmail;
        this.projectId = projectId;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }
}
