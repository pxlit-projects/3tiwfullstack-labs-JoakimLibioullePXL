package be.pxl.services;

import be.pxl.domain.Department;
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
        departmentRepository.save(mapToDepartment(departmentRequest));
    }

    @Override
    public DepartmentResponse findById(Long id) throws Exception {

        return mapToDepartmentResponse(departmentRepository.findById(id).orElseThrow(() -> new Exception("Department with ID:" + id + " doesn't exist.")));
    }

    @Override
    public List<DepartmentResponse> findAll() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map(this::mapToDepartmentResponse).toList();
    }

    @Override
    public DepartmentResponse findByOrganization(Long organizationId) {
        return departmentRepository.findByOrganizationId(organizationId);
    }

    @Override
    public DepartmentResponse findByOrganizationWithEmployees(Long organisationId, List<Employee> employees) {
        return departmentRepository.findByOrganizationWithEmployees(organisationId, employees);
    }

    private Department mapToDepartment(DepartmentRequest departmentRequest){
        return Department.builder()
                .name(departmentRequest.getName())
                .employees(departmentRequest.getEmployees())
                .position(departmentRequest.getPosition())
                .organizationId(departmentRequest.getOrganizationId())
                .build();
    }

    private DepartmentResponse mapToDepartmentResponse(Department department){
        return DepartmentResponse.builder()
                .id(department.getId())
                .position(department.getPosition())
                .employees(department.getEmployees())
                .organizationId(department.getOrganizationId())
                .name(department.getName())
                .build();
    }
}
