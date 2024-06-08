package com.akash.employee_management_portal.dto;

public class SkillDTO {
    private long id;
    private String skillName;

    public SkillDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public SkillDTO(long id, String skillName) {
        this.id = id;
        this.skillName = skillName;
    }
}
