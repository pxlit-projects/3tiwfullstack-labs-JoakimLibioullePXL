package be.pxl.services;

import be.pxl.domain.dto.EmployeeRequest;
import be.pxl.domain.dto.EmployeeResponse;

import java.util.List;

public interface IEmployeeService {
    void add(EmployeeRequest employeeRequest);
    EmployeeResponse findById(Long id) throws Exception;
    List<EmployeeResponse> findAll();
    EmployeeResponse findByDepartment(Long departmentId) throws Exception;
    EmployeeResponse findByOrganization(Long organizationId)throws Exception;
}
