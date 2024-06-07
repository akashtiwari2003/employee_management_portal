package com.akash.employee_management_portal.entity.compositekeys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class EmployeeSkillCompositeKey implements Serializable {
    @Column(name = "employee_email")
    private String employeeEmail;
    @Column(name = "skill_id")
    private long skillId;

    public EmployeeSkillCompositeKey() {
    }

    public EmployeeSkillCompositeKey(String employeeEmail, long skillId) {
        this.employeeEmail = employeeEmail;
        this.skillId = skillId;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public long getSkillId() {
        return skillId;
    }

    public void setSkillId(long skillId) {
        this.skillId = skillId;
    }
}
