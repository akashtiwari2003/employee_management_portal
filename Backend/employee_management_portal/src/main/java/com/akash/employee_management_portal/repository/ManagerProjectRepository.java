package com.akash.employee_management_portal.repository;
import com.akash.employee_management_portal.dto.AvailableProjectDTO;
import com.akash.employee_management_portal.dto.ManagerProjectDTO;
import com.akash.employee_management_portal.entity.ManagerProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ManagerProjectRepository extends JpaRepository<ManagerProject, String> {
    ManagerProject findByManagerEmail(String managerEmail);
    ManagerProject findByProjectId(long projectId);
    @Query("SELECT new com.akash.employee_management_portal.dto.AvailableProjectDTO(p.projectId, p.projectName, p.projectDesc) FROM Project p LEFT JOIN ManagerProject mp ON p.projectId = mp.projectId WHERE mp.projectId IS NULL")
    List<AvailableProjectDTO> findProjectsNotManaged();

}
