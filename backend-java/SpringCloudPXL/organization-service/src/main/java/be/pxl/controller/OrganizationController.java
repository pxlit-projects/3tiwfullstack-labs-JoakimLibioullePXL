package be.pxl.controller;

import be.pxl.domain.Department;
import be.pxl.domain.Employee;
import be.pxl.domain.dto.OrganizationResponse;
import be.pxl.services.IOrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organization")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class OrganizationController {
    private final IOrganizationService organizationService;

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable Long id) throws Exception {
        try{
            return new ResponseEntity(organizationService.findById(id), HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException(e);

        }
    }

    @GetMapping("{id}/with-departments")
    public ResponseEntity findByIdWithDepartments(@PathVariable Long id, @RequestBody List<Department> departments) throws Exception {
        try{
            return new ResponseEntity(organizationService.findByIdWithDepartments(id, departments), HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException(e);

        }
    }

    @GetMapping("{id}/with-departments-and-employees")
    public ResponseEntity findByIdWithDepartmentsAndEmployees(@PathVariable Long id, @RequestBody List<Department> departments, @RequestBody List<Employee> employees) throws Exception {
        try{
            return new ResponseEntity(organizationService.findByIdWithDepartmentsAndEmployees(id, departments, employees), HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException(e);

        }
    }

    @GetMapping("{id}/with-departments")
    public ResponseEntity findByIdWithEmployees(@PathVariable Long id, @RequestBody List<Employee> employees) throws Exception {
        try{
            return new ResponseEntity(organizationService.findByIdWithEmployees(id, employees), HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException(e);

        }
    }
}
