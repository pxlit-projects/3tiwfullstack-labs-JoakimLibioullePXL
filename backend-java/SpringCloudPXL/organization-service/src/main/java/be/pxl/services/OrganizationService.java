package be.pxl.services;

import be.pxl.domain.Department;
import be.pxl.domain.Employee;
import be.pxl.domain.Organization;
import be.pxl.domain.dto.OrganizationResponse;
import be.pxl.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService implements IOrganizationService{
    private final OrganizationRepository organizationRepository;
    @Override
    public OrganizationResponse findById(Long id) throws Exception {
        return mapToOrganizationResponse(organizationRepository.findById(id).orElseThrow(() -> new Exception("Organization with ID:" + id + " doesn't exist.")));
    }

    @Override
    public OrganizationResponse findByIdWithDepartments(Long id, List<Department> departments) {
        return organizationRepository.findByIdWithDepartments(id, departments);
    }

    @Override
    public OrganizationResponse findByIdWithDepartmentsAndEmployees(Long id, List<Department> departments, List<Employee> employees) {
        return organizationRepository.findByIdWithDepartmentsAndEmployees(id, departments, employees);
    }

    @Override
    public OrganizationResponse findByIdWithEmployees(Long id, List<Employee> employees) {
        return organizationRepository.findByIdWithEmployees(id, employees);
    }

    private OrganizationResponse mapToOrganizationResponse(Organization organization){
        return OrganizationResponse.builder()
                .name(organization.getName())
                .address(organization.getAddress())
                .departments(organization.getDepartments())
                .employees(organization.getEmployees())
                .id(organization.getId())
                .build();
    }
}
