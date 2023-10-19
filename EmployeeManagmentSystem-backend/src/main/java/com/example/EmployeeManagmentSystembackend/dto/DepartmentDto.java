package com.example.EmployeeManagmentSystembackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    //we use this call to transfer data between server and client
    public Long id;
    public String departmentName;
    public String departmentDescription;
}
