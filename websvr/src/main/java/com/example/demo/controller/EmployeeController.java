package com.example.demo.controller;

import com.example.demo.domain.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/emp")
public class EmployeeController {
    //
    @RequestMapping(value = "/post3", method = RequestMethod.POST)
    public List<Employee> post3(@RequestBody Employee employee){
        List<Employee> result = new ArrayList<>();
        Employee emp1 = new Employee();
        emp1.setName("R1");
        emp1.setAge(26);
        emp1.setDesignation("高级程序员");
        emp1.setSalary(1000);
//        emp1.printEmployee();

        Employee emp2 = new Employee();
        emp2.setName("R2");
        emp2.setAge(21);
        emp2.setDesignation("初级程序员");
        emp2.setSalary(500);
//        emp2.printEmployee();

        result.add(emp1);
        result.add(emp2);
        result.add(employee);

        return result;
    }
}
