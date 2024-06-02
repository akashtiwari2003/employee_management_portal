package com.akash.employee_management_portal;

import com.akash.employee_management_portal.dto.SkillRequest;
import com.akash.employee_management_portal.entity.Skill;
import com.akash.employee_management_portal.repository.SkillRepository;
import com.akash.employee_management_portal.service.SkillServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SkillServiceImplTest {
    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private SkillServiceImpl skillServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateSkill() {
        SkillRequest skillRequest = new SkillRequest();
        skillRequest.setSkillName("Java");
        Skill skill = new Skill(skillRequest.getSkillName());
        when(skillRepository.save(any(Skill.class))).thenReturn(skill);
        ResponseEntity<String> response = skillServiceImpl.createSkill(skillRequest);
        verify(skillRepository).save(any(Skill.class));
        assertEquals("Skill Created", response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

}
