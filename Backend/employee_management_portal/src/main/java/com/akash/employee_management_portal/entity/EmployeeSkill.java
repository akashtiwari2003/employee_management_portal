package com.akash.employee_management_portal.entity;


import com.akash.employee_management_portal.entity.compositekeys.EmployeeSkillCompositeKey;
import jakarta.persistence.*;

@Entity
@Table(name = "employee_skills")
@IdClass(EmployeeSkillCompositeKey.class)
public class EmployeeSkill {
    @Id
    @Column(name = "employee_email")
    private String employeeEmail;

    @Id
    @Column(name = "skill_id")
    private long skillId;

    @ManyToOne
    @JoinColumn(name = "employee_email", referencedColumnName = "email")
    private User user;

    @ManyToOne
    @JoinColumn(name = "skill_id", referencedColumnName = "id")
    private Skill skill;

    public EmployeeSkill() {
    }

    public EmployeeSkill(String employeeEmail, long skillId) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }
}
