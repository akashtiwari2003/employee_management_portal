package com.akash.employee_management_portal;

import com.akash.employee_management_portal.dto.SkillDTO;
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

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SkillServiceImplTest {

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private SkillServiceImpl skillService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateSkill() {
        SkillRequest skillRequest = new SkillRequest();
        skillRequest.setSkillName("Java");

        Skill skill = new Skill("Java");

        when(skillRepository.save(any(Skill.class))).thenReturn(skill);

        ResponseEntity<String> response = skillService.createSkill(skillRequest);

        assertEquals("Skill Created", response.getBody());
        verify(skillRepository, times(1)).save(any(Skill.class));
    }

    @Test
    void testFindAllSkills() {
        Skill skill1 = new Skill("Java");
        skill1.setId(1L);
        Skill skill2 = new Skill("Python");
        skill2.setId(2L);

        when(skillRepository.findAll()).thenReturn(Arrays.asList(skill1, skill2));

        List<SkillDTO> skillDTOs = skillService.findAllSkills();

        assertEquals(2, skillDTOs.size());
        assertEquals("Java", skillDTOs.get(0).getSkillName());
        assertEquals("Python", skillDTOs.get(1).getSkillName());
    }
}
