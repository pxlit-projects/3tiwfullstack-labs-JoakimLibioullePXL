package be.pxl.services;

import be.pxl.domain.Employee;
import be.pxl.domain.dto.DepartmentRequest;
import be.pxl.domain.dto.DepartmentResponse;

import java.util.List;

public interface IDepartmentService {
    void add(DepartmentRequest departmentRequest);
    DepartmentResponse findById(Long id) throws Exception;
    List<DepartmentResponse> findAll();
    DepartmentResponse findByOrganization(Long organizationId);
    DepartmentResponse findByOrganizationWithEmployees(Long organisationId, List<Employee> employees);
}
