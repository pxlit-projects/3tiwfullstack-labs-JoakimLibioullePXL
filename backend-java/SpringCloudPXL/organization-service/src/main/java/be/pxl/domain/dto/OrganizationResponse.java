package be.pxl.domain.dto;

import be.pxl.domain.Department;
import be.pxl.domain.Employee;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationResponse {
    private Long id;
    private String name;
    private String address;
    private List<Employee> employees;
    private List<Department> departments;
}
