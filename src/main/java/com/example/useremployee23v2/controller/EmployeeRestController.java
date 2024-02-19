package com.example.useremployee23v2.controller;

import com.example.useremployee23v2.model.Employee;
import com.example.useremployee23v2.model.User;
import com.example.useremployee23v2.repository.EmployeeRepository;
import com.example.useremployee23v2.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    //@Transactional
    public ResponseEntity<Employee> postEmployee(@RequestBody Employee employee) {
        System.out.println(employee);
        //userRepository.save(employee.getUser());
        employeeRepository.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employeeRepository.deleteById(id);
            return ResponseEntity.ok("Employee delete");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found id=" + id);
        }
    }


}


