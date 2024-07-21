package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model theModel){
        List<Employee> theEmployees = employeeService.findAll();
        theModel.addAttribute("employees",theEmployees);
        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String addEmployee(Model theModel){
        theModel.addAttribute("employee",new Employee());
        return "employees/employee-form";
    }

    @GetMapping("/showFormForUpdate")
    public String updateEmployee(@RequestParam("employeeId")int id, Model theModel){
        Employee employee = employeeService.findById(id);
        theModel.addAttribute("employee",employee);
        return "employees/employee-form";
    }

    @PostMapping ("/save")
    public String addEmployee(@ModelAttribute("employee")Employee employee){
        employeeService.save(employee);
        return "redirect:/employees/list";
    }

    @GetMapping ("/delete")
    public String addEmployee(@RequestParam("employeeId")int employeeId){
        employeeService.deleteById(employeeId);
        return "redirect:/employees/list";
    }
}
