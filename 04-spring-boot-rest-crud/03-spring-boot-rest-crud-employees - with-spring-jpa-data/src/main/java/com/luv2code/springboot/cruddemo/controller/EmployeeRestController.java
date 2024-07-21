package com.luv2code.springboot.cruddemo.controller;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;
    //quick and dirty : inject employeeDao
    public EmployeeRestController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeid}")
    public Employee GetById(@PathVariable Integer employeeid){
        Employee employee =  employeeService.getById(employeeid);
        if(employee==null){
            throw new RuntimeException("Employee not found - "+ employeeid);
        }
        return employee;
    }

    @PostMapping ("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        employee.setId(0);
        return employeeService.save(employee);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @DeleteMapping ("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable Integer employeeId){
        Employee employee = employeeService.getById(employeeId);
        if(employee==null){
            throw new RuntimeException("Not found employee with id : " + employeeId);
        }
        employeeService.delete(employeeId);
        return "Deleted employee id - " + employeeId;
    }
}
