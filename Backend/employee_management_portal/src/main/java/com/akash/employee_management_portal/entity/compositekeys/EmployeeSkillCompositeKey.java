package com.akash.employee_management_portal.entity.compositekeys;

import java.io.Serializable;

public class EmployeeSkillCompositeKey implements Serializable {
    private String employeeEmail;
    private long skillId;

    public EmployeeSkillCompositeKey(String employeeEmail, long skillId) {
        this.employeeEmail = employeeEmail;
        this.skillId = skillId;
    }
}
