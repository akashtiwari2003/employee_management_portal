package com.akash.employee_management_portal.dto;

import com.akash.employee_management_portal.entity.Project;

public class ProjectDetails {
    private String employeeEmail;
    private long projectId;
    private String managerEmail;
    private String projectnName;
    private String projectDesc;

    public ProjectDetails() {
    }

    public ProjectDetails(String employeeEmail, long projectId, String managerEmail, String projectnName, String projectDesc) {
        this.employeeEmail = employeeEmail;
        this.projectId = projectId;
        this.managerEmail = managerEmail;
        this.projectnName = projectnName;
        this.projectDesc = projectDesc;
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

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public String getProjectnName() {
        return projectnName;
    }

    public void setProjectnName(String projectnName) {
        this.projectnName = projectnName;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }
}
