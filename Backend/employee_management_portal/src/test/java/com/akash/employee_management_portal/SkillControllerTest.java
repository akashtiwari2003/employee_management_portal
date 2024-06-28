package com.akash.employee_management_portal;

import com.akash.employee_management_portal.dto.SkillDTO;
import com.akash.employee_management_portal.dto.SkillRequest;
import com.akash.employee_management_portal.rest.SkillController;
import com.akash.employee_management_portal.service.SkillService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SkillControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SkillService skillService;

    @InjectMocks
    private SkillController skillController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(skillController).build();
    }

    @Test
    void testFindAllSkills() throws Exception {
        SkillDTO skillDTO1 = new SkillDTO(1L, "Java");
        SkillDTO skillDTO2 = new SkillDTO(2L, "Python");

        when(skillService.findAllSkills()).thenReturn(Arrays.asList(skillDTO1, skillDTO2));

        mockMvc.perform(get("/skills"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].skillName").value("Java"))
                .andExpect(jsonPath("$[1].skillName").value("Python"));

        verify(skillService, times(1)).findAllSkills();
    }

    @Test
    void testCreateSkill() throws Exception {
        SkillRequest skillRequest = new SkillRequest();
        skillRequest.setSkillName("Java");

        when(skillService.createSkill(any(SkillRequest.class))).thenReturn(ResponseEntity.ok("Skill Created"));

        mockMvc.perform(post("/skill")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"skillName\":\"Java\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Skill Created"));

        verify(skillService, times(1)).createSkill(any(SkillRequest.class));
    }
}
