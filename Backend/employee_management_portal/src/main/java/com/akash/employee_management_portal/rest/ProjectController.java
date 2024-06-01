package com.akash.employee_management_portal.rest;

import com.akash.employee_management_portal.entity.Project;
import com.akash.employee_management_portal.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
