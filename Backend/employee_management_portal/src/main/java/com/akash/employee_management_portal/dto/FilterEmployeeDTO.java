package com.akash.employee_management_portal.dto;

public class FilterEmployeeDTO {
    private String name;
    private long projectID;
    private String skillName;

    public FilterEmployeeDTO() {
    }

    public FilterEmployeeDTO(String name, long projectID, String skillName) {
        this.name = name;
        this.projectID = projectID;
        this.skillName = skillName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
}
