package be.pxl.controller;

import be.pxl.DepartmentServiceApplication;
import be.pxl.domain.dto.DepartmentRequest;
import be.pxl.repository.DepartmentRepository;
import be.pxl.repository.EmployeeRepository;
import be.pxl.services.IDepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = DepartmentServiceApplication.class)
@Testcontainers
@AutoConfigureMockMvc(addFilters = false) // Uitschakelen van security filters
public class DepartmentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Container
    private static MySQLContainer sqlContainer = new MySQLContainer("mysql:5.7.37");

    @DynamicPropertySource
    static void registerMySQLProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", sqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", sqlContainer::getUsername);
        registry.add("spring.datasource.password", sqlContainer::getPassword);
    }

    @Test
    public void testAddDepartment() throws Exception {
        DepartmentRequest request = new DepartmentRequest();
        request.setName("HR");
        request.setOrganizationId(1L);

        mockMvc.perform(post("/api/department")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindDepartmentById() throws Exception {
        Long departmentId = 1L;

        mockMvc.perform(get("/api/department/{id}", departmentId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testFindAllDepartments() throws Exception {
        mockMvc.perform(get("/api/department"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testFindDepartmentsByOrganization() throws Exception {
        Long organizationId = 1L;

        mockMvc.perform(get("/api/department/organization/{organizationId}", organizationId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testFindDepartmentsByOrganizationWithEmployees() throws Exception {
        Long organizationId = 1L;

        mockMvc.perform(get("/api/department/organization/{organizationId}/with-employees", organizationId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[]")) // For empty employee list, replace with actual employee data if needed
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
