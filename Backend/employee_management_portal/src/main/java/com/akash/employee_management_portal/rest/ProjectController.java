package com.akash.employee_management_portal.rest;

import com.akash.employee_management_portal.dto.ProjectDTO;
import com.akash.employee_management_portal.entity.Project;
import com.akash.employee_management_portal.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/all")
    public List<Project> getProducts(){
        return projectService.findAll();
    }

    @PostMapping("add")
    public ResponseEntity<String> createProject(@RequestBody ProjectDTO projectDTO) {
        return projectService.createProject(projectDTO);
    }

    @DeleteMapping("remove/{projectId}")
    public ResponseEntity<String> removeProject(@PathVariable("projectId") long projectId){
        return projectService.removeProject(projectId);
    }
}
