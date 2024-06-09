package com.akash.employee_management_portal.service;

import com.akash.employee_management_portal.dto.ProjectDTO;
import com.akash.employee_management_portal.entity.ManagerProject;
import com.akash.employee_management_portal.entity.Project;
import com.akash.employee_management_portal.repository.EmployeeProjectRepository;
import com.akash.employee_management_portal.repository.ManagerProjectRepository;
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
        Project createdProject = projectRepository.findByProjectName(project.getProjectName());
        return ResponseEntity.ok("Project Created with ID : " + createdProject.getProjectId());
    }

    @Override
    public ResponseEntity<String> removeProject(long projectId) {
        Project project = projectRepository.findByProjectId(projectId);
        projectRepository.delete(project);
        return ResponseEntity.ok("Project Removed");
    }
}
