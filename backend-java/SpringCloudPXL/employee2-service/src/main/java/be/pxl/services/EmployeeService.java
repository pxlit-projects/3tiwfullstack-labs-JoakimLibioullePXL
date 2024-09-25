package be.pxl.services;

import be.pxl.domain.Employee;
import be.pxl.domain.dto.EmployeeRequest;
import be.pxl.domain.dto.EmployeeResponse;
import be.pxl.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService{
    private final EmployeeRepository employeeRepository;
    @Override
    public void add(EmployeeRequest employeeRequest) {
        Employee employee = MapToEmployee(employeeRequest);
        employeeRepository.save(employee);
    }

    @Override
    public EmployeeResponse findById(Long id) throws Exception {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new Exception("Emmployee with ID:" + id + " doesn't exist."));
        return MapToEmployeeResponse(employee);
    }

    @Override
    public List<EmployeeResponse> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(this::MapToEmployeeResponse).toList();
    }

    @Override
    public EmployeeResponse findByDepartment(Long departmentId) throws Exception{
        return employeeRepository.findByDepartmentId(departmentId);
    }

    @Override
    public EmployeeResponse findByOrganization(Long organizationId) {
        return employeeRepository.findByOrganizationId(organizationId);
    }

    private Employee MapToEmployee(EmployeeRequest employeeRequest){
        return Employee.builder()
                .name(employeeRequest.getName())
                .age(employeeRequest.getAge())
                .departmentId(employeeRequest.getDepartmentId())
                .organizationId(employeeRequest.getOrganizationId())
                .position(employeeRequest.getPosition())
                .build();
    }

    private EmployeeResponse MapToEmployeeResponse(Employee employee){
        return EmployeeResponse.builder()
                .id(employee.getId())
                .age(employee.getAge())
                .departmentId(employee.getDepartmentId())
                .organizationId(employee.getOrganizationId())
                .name(employee.getName())
                .position(employee.getPosition())
                .build();
    }
}
