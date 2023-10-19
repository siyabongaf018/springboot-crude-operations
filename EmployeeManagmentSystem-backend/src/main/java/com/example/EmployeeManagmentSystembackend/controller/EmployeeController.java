package com.example.EmployeeManagmentSystembackend.controller;

import com.example.EmployeeManagmentSystembackend.dto.EmployeeDto;
import com.example.EmployeeManagmentSystembackend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@AllArgsConstructor
@RestController //this class becomes spring mvc rest controller & it enables it to handle http request
@RequestMapping("/api/employees") // this defines the base url for all request
public class EmployeeController {

    private EmployeeService employeeService;
    /*build Add Employee rest api
    @PostMapping map  incoming http request to this method
    @RequestBody Extract the json from the http request and convert it to the object
    ResponseEntity<> for http response

    http://localhost:8080/api/employees
    json
    {
    "firstName": "siya",
    "lastName": "chibi",
    "email": "siya@gmail.com"
}
     */
    @PostMapping
    public ResponseEntity<EmployeeDto> creaateEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
    /*
    Build get employee rest api
    http://localhost:8080/api/employees/1

     */

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeId(@PathVariable("id")Long employeeId){
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);
    }
    /*
     Build Get all employees rest api
     */
    @GetMapping
    public ResponseEntity<List <EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> employees =  employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    /*
    Build update employee rest api
     */
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id")Long employeeId,
                                                      @RequestBody EmployeeDto updatedEmployee){
        EmployeeDto employeeDto = employeeService.updateEmployee(employeeId, updatedEmployee);
        return ResponseEntity.ok(employeeDto);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable("id")Long employeeId){
         employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee successfully deleted");
    }



}
