package be.pxl.controller;

import be.pxl.domain.Employee;
import be.pxl.domain.dto.DepartmentRequest;
import be.pxl.domain.dto.DepartmentResponse;
import be.pxl.services.IDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class DepartmentController {
    private final IDepartmentService departmentService;

    @PostMapping
    public void add(@RequestBody DepartmentRequest departmentRequest){
        departmentService.add(departmentRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        try {
            return new ResponseEntity(departmentService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public ResponseEntity findAll(){
        try {
            return new ResponseEntity(departmentService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/organization/{organizationId}")
    public ResponseEntity findByOrganization(@PathVariable Long organizationId){
        try {
            return new ResponseEntity(departmentService.findByOrganization(organizationId), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/organization/{organizationId}/with-employees")
    public ResponseEntity findByOrganizationWithEmployees(@PathVariable Long organizationId, @RequestBody List<Employee> employees){
        try {
            return new ResponseEntity(departmentService.findByOrganizationWithEmployees(organizationId, employees), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
