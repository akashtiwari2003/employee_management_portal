package com.akash.employee_management_portal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "manager_projects")
public class ManagerProject {
    @Id
    @Column(name = "manager_email")
    private String managerEmail;

    @Column(name = "project_id")
    private long projectId;

    public ManagerProject() {
    }

    public ManagerProject(String managerEmail, long projectId) {
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

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }
}
