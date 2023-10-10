package com.example.EmployeeManagmentSystembackend.mapper;

import com.example.EmployeeManagmentSystembackend.dto.EmployeeDto;
import com.example.EmployeeManagmentSystembackend.entity.Employee;

public class EmployeeMapper {


    //this method will map Employee into EmployeeDto
    public  static EmployeeDto mapToEmployeeDto(Employee employee){
        return new EmployeeDto(employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
                );
    }

    //this method will map EmployeeDto into Employee
    public  static Employee mapToEmployee(EmployeeDto employeeDto){
        return new Employee(employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail());
    }
}
