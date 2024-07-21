package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> getAll();
    Employee getById(Integer id);
    Employee save(Employee employee);
    void  delete(Integer id);
}
