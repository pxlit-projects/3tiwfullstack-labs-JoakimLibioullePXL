package be.pxl.services;

import be.pxl.domain.Department;
import be.pxl.domain.Employee;
import be.pxl.domain.dto.OrganizationResponse;

import java.util.List;

public interface IOrganizationService {
    OrganizationResponse findById(Long id) throws Exception;
    OrganizationResponse findByIdAndDepartmentsIn(Long id, List<Department> departments);
    OrganizationResponse findByIdAndDepartmentsInAndEmployeesIn(Long id, List<Department> departments, List<Employee> employees);
    OrganizationResponse findByIdAndEmployeesIn(Long id, List<Employee> employees);
}
