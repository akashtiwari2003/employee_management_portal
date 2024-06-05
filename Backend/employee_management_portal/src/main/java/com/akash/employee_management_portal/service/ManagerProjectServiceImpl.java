package com.akash.employee_management_portal.service;

import com.akash.employee_management_portal.dto.AvailableProjectDTO;
import com.akash.employee_management_portal.dto.ManagerProjectDTO;
import com.akash.employee_management_portal.entity.ManagerProject;
import com.akash.employee_management_portal.repository.ManagerProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerProjectServiceImpl implements ManagerProjectService{

    @Autowired
    ManagerProjectRepository managerProjectRepository;

    @Override
    public ResponseEntity<String> assignManager(ManagerProjectDTO managerProjectDTO) {
        ManagerProject managerProject = new ManagerProject(managerProjectDTO.getManagerEmail(), managerProjectDTO.getProjectId());
        managerProjectRepository.save(managerProject);
        return ResponseEntity.ok("Manager Assigned Project");
    }

    @Override
    public ResponseEntity<String> unassignManager(String managerEmail) {
        ManagerProject managerProject = managerProjectRepository.findByManagerEmail(managerEmail);
        managerProjectRepository.delete(managerProject);
        return ResponseEntity.ok("Project Unassigned from Manager");
    }

    @Override
    public List<AvailableProjectDTO> findProjectsNotManaged() {
        return managerProjectRepository.findProjectsNotManaged();
    }
}
