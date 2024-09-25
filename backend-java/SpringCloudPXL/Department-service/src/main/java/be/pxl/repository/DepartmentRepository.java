package be.pxl.repository;

import be.pxl.domain.Department;
import be.pxl.domain.Employee;
import be.pxl.domain.dto.DepartmentResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    DepartmentResponse findByOrganizationId(Long organisationId);
    DepartmentResponse findByOrganizationWithEmployees(Long organisationId, List<Employee> employees);
}
