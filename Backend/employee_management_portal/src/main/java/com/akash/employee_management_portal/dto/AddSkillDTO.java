package com.akash.employee_management_portal.dto;

public class AddSkillDTO {
    private String employeeEmail;
    private String skillName;

    public AddSkillDTO(String employeeEmail, String skillName) {
        this.employeeEmail = employeeEmail;
        this.skillName = skillName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
}
