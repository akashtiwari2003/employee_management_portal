package com.akash.employee_management_portal.dto;

public class EmployeeSkillDTO {
    private String firstName;
    private String skillName;

    public EmployeeSkillDTO(String firstName, String skillName) {
        this.firstName = firstName;
        this.skillName = skillName;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
}
