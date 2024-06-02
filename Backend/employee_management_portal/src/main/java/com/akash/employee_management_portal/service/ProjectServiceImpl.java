package com.akash.employee_management_portal.service;

import com.akash.employee_management_portal.dto.ProjectDTO;
import com.akash.employee_management_portal.entity.Project;
import com.akash.employee_management_portal.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectRepository projectRepository;


    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public ResponseEntity<String> createProject(ProjectDTO projectDTO) {
        Project project = new Project(projectDTO.getProjectName(), projectDTO.getProjectDesc());
        projectRepository.save(project);
        return ResponseEntity.ok("Project Created");
    }
}
