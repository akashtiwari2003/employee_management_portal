package com.akash.employee_management_portal;

import com.akash.employee_management_portal.entity.EmployeeSkill;
import com.akash.employee_management_portal.entity.Skill;
import com.akash.employee_management_portal.entity.User;
import com.akash.employee_management_portal.entity.compositekeys.EmployeeSkillCompositeKey;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class EmployeeSkillTest {

    @Test
    void testNoArgsConstructor() {
        EmployeeSkill employeeSkill = new EmployeeSkill();
        assertNull(employeeSkill.getEmployeeEmail());
        assertEquals(0, employeeSkill.getSkillId());
        assertNull(employeeSkill.getUser());
        assertNull(employeeSkill.getSkill());
    }

    @Test
    void testAllArgsConstructor() {
        EmployeeSkillCompositeKey compositeKey = new EmployeeSkillCompositeKey("employee@example.com", 12345L);
        EmployeeSkill employeeSkill = new EmployeeSkill(compositeKey);

        assertEquals(compositeKey.getEmployeeEmail(), employeeSkill.getEmployeeEmail());
        assertEquals(compositeKey.getSkillId(), employeeSkill.getSkillId());
    }

    @Test
    void testSetEmployeeEmail() {
        EmployeeSkill employeeSkill = new EmployeeSkill();
        String employeeEmail = "employee@example.com";
        employeeSkill.setEmployeeEmail(employeeEmail);
        assertEquals(employeeEmail, employeeSkill.getEmployeeEmail());
    }

    @Test
    void testSetSkillId() {
        EmployeeSkill employeeSkill = new EmployeeSkill();
        long skillId = 12345L;
        employeeSkill.setSkillId(skillId);
        assertEquals(skillId, employeeSkill.getSkillId());
    }

    @Test
    void testSetUser() {
        EmployeeSkill employeeSkill = new EmployeeSkill();
        User user = Mockito.mock(User.class);
        employeeSkill.setUser(user);
        assertEquals(user, employeeSkill.getUser());
    }

    @Test
    void testSetSkill() {
        EmployeeSkill employeeSkill = new EmployeeSkill();
        Skill skill = Mockito.mock(Skill.class);
        employeeSkill.setSkill(skill);
        assertEquals(skill, employeeSkill.getSkill());
    }

    @Test
    void testGetEmployeeEmail() {
        String employeeEmail = "employee@example.com";
        EmployeeSkill employeeSkill = new EmployeeSkill();
        employeeSkill.setEmployeeEmail(employeeEmail);
        assertEquals(employeeEmail, employeeSkill.getEmployeeEmail());
    }

    @Test
    void testGetSkillId() {
        long skillId = 12345L;
        EmployeeSkill employeeSkill = new EmployeeSkill();
        employeeSkill.setSkillId(skillId);
        assertEquals(skillId, employeeSkill.getSkillId());
    }

    @Test
    void testGetUser() {
        User user = Mockito.mock(User.class);
        EmployeeSkill employeeSkill = new EmployeeSkill();
        employeeSkill.setUser(user);
        assertEquals(user, employeeSkill.getUser());
    }

    @Test
    void testGetSkill() {
        Skill skill = Mockito.mock(Skill.class);
        EmployeeSkill employeeSkill = new EmployeeSkill();
        employeeSkill.setSkill(skill);
        assertEquals(skill, employeeSkill.getSkill());
    }
}
