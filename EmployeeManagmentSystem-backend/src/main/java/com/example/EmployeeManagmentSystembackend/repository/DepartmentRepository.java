package com.example.EmployeeManagmentSystembackend.repository;

import com.example.EmployeeManagmentSystembackend.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
