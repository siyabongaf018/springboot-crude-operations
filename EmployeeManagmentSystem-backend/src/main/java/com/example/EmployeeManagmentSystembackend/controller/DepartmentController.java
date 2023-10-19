package com.example.EmployeeManagmentSystembackend.controller;

import com.example.EmployeeManagmentSystembackend.dto.DepartmentDto;
import com.example.EmployeeManagmentSystembackend.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private DepartmentService departmentService;

    @PostMapping()
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto createDepartment = departmentService.createDepartment(departmentDto);
        return new ResponseEntity<>(createDepartment, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody DepartmentDto departmentDto,
                                                          @PathVariable("id") Long departmentId){
        DepartmentDto updateDepartment = departmentService.updateDepartment(departmentId,departmentDto);
        return ResponseEntity.ok(updateDepartment);

    }

    @GetMapping("{id}")
    public ResponseEntity<DepartmentDto> getAllDepartmentByID(@PathVariable("id") Long departmentId){
        DepartmentDto departmentDetails = departmentService.getDepartmentById(departmentId);

        return ResponseEntity.ok(departmentDetails);
    }
    @GetMapping()
    public ResponseEntity<List<DepartmentDto>> getAllDepartment(){
        List<DepartmentDto> Departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(Departments);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long departmentId){
        departmentService.deleteDepartment(departmentId);
        return ResponseEntity.ok("Department successfully deleted");

    }
}
