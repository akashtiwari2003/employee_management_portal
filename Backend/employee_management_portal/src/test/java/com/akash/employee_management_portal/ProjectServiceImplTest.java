package com.akash.employee_management_portal;

import com.akash.employee_management_portal.entity.Project;
import com.akash.employee_management_portal.repository.ProjectRepository;
import com.akash.employee_management_portal.service.ProjectServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;

public class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Test
    public void testFindAll() {
        //List<Project> projects = Arrays.asList(new Project(1,"",""))
    }

}
