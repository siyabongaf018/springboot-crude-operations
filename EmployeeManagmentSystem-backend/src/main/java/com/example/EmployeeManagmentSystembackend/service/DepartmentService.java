package com.example.EmployeeManagmentSystembackend.service;

import com.example.EmployeeManagmentSystembackend.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartmentById(Long departmentId);
    List<DepartmentDto> getAllDepartments();
    DepartmentDto updateDepartment (Long departmentId, DepartmentDto departmentDto);
    void deleteDepartment(Long departmentId);
}
