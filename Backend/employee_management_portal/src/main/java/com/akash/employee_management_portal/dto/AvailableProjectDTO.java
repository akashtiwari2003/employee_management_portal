package com.akash.employee_management_portal.dto;

public class AvailableProjectDTO {
    private long projectId;
    private String projectName;
    private String projectDesc;

    public AvailableProjectDTO(long projectId, String projectName, String projectDesc) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDesc = projectDesc;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }
}
