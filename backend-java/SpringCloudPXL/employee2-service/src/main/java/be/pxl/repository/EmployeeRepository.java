package be.pxl.repository;

import be.pxl.domain.Employee;
import be.pxl.domain.dto.EmployeeResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    EmployeeResponse findByDepartmentId(Long departmentId);
    EmployeeResponse findByOrganizationId(Long organisationId);
}
