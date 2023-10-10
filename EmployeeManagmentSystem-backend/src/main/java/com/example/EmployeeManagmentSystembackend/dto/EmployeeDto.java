package com.example.EmployeeManagmentSystembackend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    //we use this call to transfer data between server and client
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

}
