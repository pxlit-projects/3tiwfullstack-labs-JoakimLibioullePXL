package be.pxl.services;

import be.pxl.domain.Employee;
import be.pxl.domain.dto.DepartmentRequest;
import be.pxl.domain.dto.DepartmentResponse;
import be.pxl.repository.DepartmentRepository;
import be.pxl.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DepartmentService implements IDepartmentService{
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    @Override
    public void add(DepartmentRequest departmentRequest) {
        
    }

    @Override
    public DepartmentResponse findById(Long id) throws Exception {
        return null;
    }

    @Override
    public List<DepartmentResponse> findAll() {
        return null;
    }

    @Override
    public DepartmentResponse findByOrganization(Long organizationId) {
        return null;
    }

    @Override
    public DepartmentResponse findByOrganizationWithEmployees(Long organisationId, List<Employee> employees) {
        return null;
    }
}
