package com.akash.employee_management_portal;

import com.akash.employee_management_portal.dto.AddSkillDTO;
import com.akash.employee_management_portal.entity.compositekeys.EmployeeSkillCompositeKey;
import com.akash.employee_management_portal.rest.EmployeeSkillController;
import com.akash.employee_management_portal.service.EmployeeSkillService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EmployeeSkillControllerTest {

    @InjectMocks
    private EmployeeSkillController employeeSkillController;

    @Mock
    private EmployeeSkillService employeeSkillService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddSkill() {
        AddSkillDTO addSkillDTO = new AddSkillDTO("employee@example.com","Java");

        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Skill Added Successfully");

        doReturn(expectedResponse).when(employeeSkillService).addSkill(any(AddSkillDTO.class));

        ResponseEntity<String> response = employeeSkillController.addSkill(addSkillDTO);
        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());

        verify(employeeSkillService, times(1)).addSkill(any(AddSkillDTO.class));
    }

    @Test
    void testRemoveSkill() {
        EmployeeSkillCompositeKey compositeKey = new EmployeeSkillCompositeKey("employee@example.com", 1L);

        doNothing().when(employeeSkillService).deleteById(any(EmployeeSkillCompositeKey.class));

        employeeSkillController.removeSkill(compositeKey);
        verify(employeeSkillService, times(1)).deleteById(compositeKey);
    }
}
