package be.pxl.services;

import be.pxl.domain.Department;
import be.pxl.domain.Employee;
import be.pxl.domain.dto.DepartmentRequest;
import be.pxl.domain.dto.DepartmentResponse;
import be.pxl.repository.DepartmentRepository;
import be.pxl.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddDepartment() {
        DepartmentRequest departmentRequest = new DepartmentRequest();
        departmentRequest.setName("HR");
        departmentRequest.setOrganizationId(1L);

        departmentService.add(departmentRequest);

        verify(departmentRepository, times(1)).save(any(Department.class));
    }

    @Test
    void testFindById_Success() throws Exception {
        Department department = new Department();
        department.setId(1L);
        department.setName("IT");

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        DepartmentResponse result = departmentService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("IT", result.getName());
    }

    @Test
    void testFindById_NotFound() {
        when(departmentRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> departmentService.findById(1L));

        assertEquals("Department with ID:1 doesn't exist.", exception.getMessage());
    }

    @Test
    void testFindAll() {
        Department department1 = new Department();
        department1.setId(1L);
        department1.setName("IT");

        Department department2 = new Department();
        department2.setId(2L);
        department2.setName("Finance");

        List<Department> departments = Arrays.asList(department1, department2);

        when(departmentRepository.findAll()).thenReturn(departments);

        List<DepartmentResponse> result = departmentService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("IT", result.get(0).getName());
        assertEquals("Finance", result.get(1).getName());
    }

    @Test
    void testFindByOrganization_Success() {
        Department department = new Department();
        department.setId(1L);
        department.setOrganizationId(1L);
        department.setName("Finance");

        // Maak een DepartmentResponse aan met behulp van de builder
        DepartmentResponse departmentResponse = DepartmentResponse.builder()
                .id(department.getId())
                .organizationId(department.getOrganizationId())
                .name(department.getName())
                .employees(department.getEmployees())
                .position(department.getPosition())
                .build();

        when(departmentRepository.findByOrganizationId(1L)).thenReturn(departmentResponse);

        DepartmentResponse result = departmentService.findByOrganization(1L);

        assertNotNull(result);
        assertEquals(1L, result.getOrganizationId());
        assertEquals("Finance", result.getName());
    }


    @Test
    void testFindByOrganizationWithEmployees() {
        // Maak de department aan
        Department department = new Department();
        department.setId(1L);
        department.setOrganizationId(1L);
        department.setName("IT");

        // Maak medewerkers aan
        Employee employee1 = new Employee(1L, 1L, 1L, "Alice", 30, "Developer");
        Employee employee2 = new Employee(2L, 1L, 1L, "Bob", 28, "Tester");

        List<Employee> employees = Arrays.asList(employee1, employee2);
        department.setEmployees(employees); // Voeg medewerkers toe aan het department

        // Maak een DepartmentResponse aan met behulp van de builder
        DepartmentResponse departmentResponse = DepartmentResponse.builder()
                .id(department.getId())
                .organizationId(department.getOrganizationId())
                .name(department.getName())
                .employees(employees) // Voeg de medewerkers toe aan de response
                .position(department.getPosition())
                .build();

        // Mock het gedrag van de repository
        when(departmentRepository.findByOrganizationWithEmployees(1L, employees))
                .thenReturn(departmentResponse);

        // Voer de service aanroep uit
        DepartmentResponse result = departmentService.findByOrganizationWithEmployees(1L, employees);

        // Controleer de resultaten
        assertNotNull(result);
        assertEquals("IT", result.getName()); // Controleer de naam van de afdeling
        assertEquals(1L, result.getOrganizationId());
        assertEquals(2, result.getEmployees().size());
        assertEquals("Alice", result.getEmployees().get(0).getName()); // Controleer de naam van de eerste medewerker
        assertEquals("Bob", result.getEmployees().get(1).getName()); // Controleer de naam van de tweede medewerker
    }

}
