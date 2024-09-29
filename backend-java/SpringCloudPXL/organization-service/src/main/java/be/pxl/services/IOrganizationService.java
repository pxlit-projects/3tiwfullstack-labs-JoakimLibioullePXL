package be.pxl.services;

import be.pxl.domain.Department;
import be.pxl.domain.Employee;
import be.pxl.domain.dto.OrganizationResponse;

import java.util.List;

public interface IOrganizationService {
    OrganizationResponse findById(Long id) throws Exception;
    OrganizationResponse findByIdWithDepartments(Long id, List<Department> departments);
    OrganizationResponse findByIdWithDepartmentsAndEmployees(Long id, List<Department> departments, List<Employee> employees);
    OrganizationResponse findByIdWithEmployees(Long id, List<Employee> employees);
}
