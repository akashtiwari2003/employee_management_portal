package com.akash.employee_management_portal;

import com.akash.employee_management_portal.dto.AddSkillDTO;
import com.akash.employee_management_portal.entity.EmployeeSkill;
import com.akash.employee_management_portal.entity.Skill;
import com.akash.employee_management_portal.entity.compositekeys.EmployeeSkillCompositeKey;
import com.akash.employee_management_portal.repository.EmployeeSkillRepository;
import com.akash.employee_management_portal.repository.SkillRepository;
import com.akash.employee_management_portal.service.EmployeeSkillServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EmployeeSkillServiceImplTest {

    @InjectMocks
    private EmployeeSkillServiceImpl employeeSkillService;

    @Mock
    private EmployeeSkillRepository employeeSkillRepository;

    @Mock
    private SkillRepository skillRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddSkill() {
        AddSkillDTO addSkillDTO = new AddSkillDTO("employee@example.com","Java");

        Skill skill = new Skill("Java");
        skill.setId(1L);

        EmployeeSkillCompositeKey compositeKey = new EmployeeSkillCompositeKey("employee@example.com", 1L);
        EmployeeSkill employeeSkill = new EmployeeSkill(compositeKey);

        when(skillRepository.findBySkillName(anyString())).thenReturn(skill);
        when(employeeSkillRepository.save(any(EmployeeSkill.class))).thenReturn(employeeSkill);

        ResponseEntity<String> response = employeeSkillService.addSkill(addSkillDTO);
        assertEquals("Skill Added Successfully", response.getBody());
    }

    @Test
    void testDeleteById() {
        EmployeeSkillCompositeKey compositeKey = new EmployeeSkillCompositeKey("employee@example.com", 1L);

        doNothing().when(employeeSkillRepository).deleteById(any(EmployeeSkillCompositeKey.class));

        employeeSkillService.deleteById(compositeKey);
        verify(employeeSkillRepository, times(1)).deleteById(compositeKey);
    }
}
