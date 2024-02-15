package com.example.useremployee23v2.controller;

import com.example.useremployee23v2.model.Employee;
import com.example.useremployee23v2.model.User;
import com.example.useremployee23v2.repository.EmployeeRepository;
import com.example.useremployee23v2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeRestController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

}


