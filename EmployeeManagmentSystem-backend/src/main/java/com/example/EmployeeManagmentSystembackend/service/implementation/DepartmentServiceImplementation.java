package com.example.EmployeeManagmentSystembackend.service.implementation;

import com.example.EmployeeManagmentSystembackend.dto.DepartmentDto;
import com.example.EmployeeManagmentSystembackend.entity.Department;
import com.example.EmployeeManagmentSystembackend.exception.ResourceNotFoundException;
import com.example.EmployeeManagmentSystembackend.mapper.DepartmentMapper;
import com.example.EmployeeManagmentSystembackend.repository.DepartmentRepository;
import com.example.EmployeeManagmentSystembackend.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service //this annotation tells spring container to create spring bean for this class
@AllArgsConstructor
public class DepartmentServiceImplementation implements DepartmentService {

    DepartmentRepository departmentRepository;
    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.mapToDepartment(departmentDto);
        Department saveDepartment = departmentRepository.save(department);

        return DepartmentMapper.mapToDepartmentDto(saveDepartment);
    }

    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {
        Department getDepartment = departmentRepository.findById(departmentId)
                .orElseThrow(()-> new ResourceNotFoundException("Department does not exists with given id : "+departmentId));

        return DepartmentMapper.mapToDepartmentDto(getDepartment);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> getAllDepartment = departmentRepository.findAll();

        return getAllDepartment.stream().map((department)->DepartmentMapper.mapToDepartmentDto(department))
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto departmentDto) {
//        Department department = DepartmentMapper.mapToDepartment(departmentDto);
        Department getDepartment = departmentRepository.findById(departmentId).orElseThrow(
                ()-> new ResourceNotFoundException("Department is not existing with given id: " + departmentId));
        getDepartment.setDepartmentName(departmentDto.getDepartmentName());
        getDepartment.setDepartmentDescription(departmentDto.getDepartmentDescription());

        Department updatedDepartment = departmentRepository.save(getDepartment);
        return DepartmentMapper.mapToDepartmentDto(updatedDepartment);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        Department getDepartment = departmentRepository.findById(departmentId).orElseThrow(
                ()-> new ResourceNotFoundException("Department is not existing with given id: " + departmentId));
        departmentRepository.deleteById(departmentId);
    }
}
