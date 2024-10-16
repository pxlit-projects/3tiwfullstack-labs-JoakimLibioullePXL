package be.pxl.repository;

import be.pxl.domain.Department;
import be.pxl.domain.Employee;
import be.pxl.domain.Organization;
import be.pxl.domain.dto.OrganizationResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    OrganizationResponse findByIdAndDepartmentsIn(Long id, List<Department> departments);
    OrganizationResponse findByIdAndDepartmentsInAndEmployeesIn(Long id, List<Department> departments, List<Employee> employees);
    OrganizationResponse findByIdAndEmployeesIn(Long id, List<Employee> employees);
}
