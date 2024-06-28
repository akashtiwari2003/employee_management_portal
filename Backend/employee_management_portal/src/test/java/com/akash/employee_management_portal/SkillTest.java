package com.akash.employee_management_portal;

import com.akash.employee_management_portal.entity.EmployeeSkill;
import com.akash.employee_management_portal.entity.Skill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SkillTest {

    private Skill skill;

    @BeforeEach
    void setUp() {
        skill = new Skill();
    }

    @Test
    void testDefaultConstructor() {
        assertNull(skill.getSkillName());
        assertNull(skill.getEmployeeSkills());
        assertEquals(0, skill.getId());
    }

    @Test
    void testParameterizedConstructor() {
        Skill parameterizedSkill = new Skill("Java");
        assertEquals("Java", parameterizedSkill.getSkillName());
    }

    @Test
    void testGetId() {
        skill.setId(1);
        assertEquals(1, skill.getId());
    }

    @Test
    void testSetId() {
        skill.setId(1);
        assertEquals(1, skill.getId());
    }

    @Test
    void testGetSkillName() {
        skill.setSkillName("Java");
        assertEquals("Java", skill.getSkillName());
    }

    @Test
    void testSetSkillName() {
        skill.setSkillName("Java");
        assertEquals("Java", skill.getSkillName());
    }

    @Test
    void testGetEmployeeSkills() {
        Set<EmployeeSkill> employeeSkills = new HashSet<>();
        skill.setEmployeeSkills(employeeSkills);
        assertEquals(employeeSkills, skill.getEmployeeSkills());
    }

    @Test
    void testSetEmployeeSkills() {
        Set<EmployeeSkill> employeeSkills = new HashSet<>();
        skill.setEmployeeSkills(employeeSkills);
        assertEquals(employeeSkills, skill.getEmployeeSkills());
    }
}
