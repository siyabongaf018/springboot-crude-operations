package com.example.EmployeeManagmentSystembackend.service.implementation;

import com.example.EmployeeManagmentSystembackend.dto.EmployeeDto;
import com.example.EmployeeManagmentSystembackend.entity.Department;
import com.example.EmployeeManagmentSystembackend.entity.Employee;
import com.example.EmployeeManagmentSystembackend.exception.ResourceNotFoundException;
import com.example.EmployeeManagmentSystembackend.mapper.EmployeeMapper;
import com.example.EmployeeManagmentSystembackend.repository.DepartmentRepository;
import com.example.EmployeeManagmentSystembackend.repository.EmployeeRepository;
import com.example.EmployeeManagmentSystembackend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service //this annotation tells spring container to create spring bean for this class
@AllArgsConstructor //this creates the constructor for the class
public class EmployeeServiceImplementation implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        //convert EmployeeDto into Employee
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        Department department = departmentRepository.findById(employeeDto.getDepartmentId())
                .orElseThrow(()->
                        new ResourceNotFoundException("Department does not exist with id:" + employeeDto.getDepartmentId()));
        employee.setDepartment(department);
        //put the data into the database
        Employee savedEmployee = employeeRepository.save(employee);

        //return the date  back to the client and we need to convert into EmployeeDto
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Employee does not exists with given id : "+ employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    /*get all the data from the database to list employees  them map through the list  as it is converted to
    mapToEmployeeDto {employees.stream().map((employee)->EmployeeMapper.mapToEmployeeDto(employee))}
    then collect the data then convert it to a list as the return type for this methode is a generic list
    {.collect(Collectors.toList())}
    */
    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream().map((employee)->EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    /*the Save methode performs two function save and update the data in the database table */
    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        //if the id exist then the object will have the id from the database
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->
                        new ResourceNotFoundException("Employee is not existing with given id: " + employeeId));

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Department department = departmentRepository.findById(updatedEmployee.getDepartmentId())
                .orElseThrow(()->
                        new ResourceNotFoundException("Department does not exist with id:" + updatedEmployee.getDepartmentId()));
        employee.setDepartment(department);

        /*save updated data on the database. if the employee has the primary key the save()
        will update the values on the table. if employee does not have the primary key it will
        add the data to the database
         */
        Employee updatedEmployeeObj =  employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);

    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->
                new ResourceNotFoundException("Employee is not existing with given id: " + employeeId));
        employeeRepository.deleteById(employeeId);

    }
}
