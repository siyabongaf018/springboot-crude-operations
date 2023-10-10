package com.example.EmployeeManagmentSystembackend.repository;

import com.example.EmployeeManagmentSystembackend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository  extends JpaRepository<Employee,Long> {
    //this repository will perform CRUD database operation in Employee Entity
}
