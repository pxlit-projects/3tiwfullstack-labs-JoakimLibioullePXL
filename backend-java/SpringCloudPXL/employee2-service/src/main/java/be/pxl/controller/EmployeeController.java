package be.pxl.controller;

import be.pxl.domain.dto.EmployeeRequest;
import be.pxl.services.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
    private final IEmployeeService employeeService;

    @PostMapping
    public void add(@RequestBody EmployeeRequest employeeRequest){
        employeeService.add(employeeRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        try {
            return new ResponseEntity(employeeService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public ResponseEntity findAll(){
        try{
            return new ResponseEntity(employeeService.findAll(), HttpStatus.OK);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity findByDepartment(@PathVariable Long departmentId){
        try {
            return new ResponseEntity(employeeService.findByDepartment(departmentId), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/organization/{organizationId}")
    public ResponseEntity findByOrganization(@PathVariable Long organizationId){
        try {
            return new ResponseEntity(employeeService.findByOrganization(organizationId), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
