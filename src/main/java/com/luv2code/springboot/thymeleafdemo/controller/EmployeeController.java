package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public void EmployeeService(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    // add mapping for the list
    @GetMapping("/list")
    public String getEmployees(Model theModel) {
        // get the employee from table
        List<Employee> employeeList = employeeService.findAll();

        // add to the spring model
        theModel.addAttribute("employees", employeeList);
        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind the data
        Employee employee = new Employee();
        theModel.addAttribute("employee", employee);

        return "/employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
        // save the employee
        employeeService.save(theEmployee);
        // use a redirect
        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(Model theModel, @RequestParam("employeeId") int theId) {

        // get the employee by id
        Employee employee = employeeService.findById(theId);

        // create model attribute to bind the data
        theModel.addAttribute("employee", employee);

        return "/employees/employee-form";
    }

    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam("employeeId") int theId) {
        employeeService.deleteById(theId);
        return "redirect:/employees/list";
    }

}
