package com.akash.employee_management_portal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee_projects")
public class EmployeeProject {
    @Id
    @Column(name = "employee_email")
    private String employeeEmail;

    @Column(name = "project_id")
    private long projectId;

    public EmployeeProject() {
    }

    public EmployeeProject(String employeeEmail, long projectId) {
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
