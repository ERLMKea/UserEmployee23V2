package com.example.useremployee23v2.repository;

import com.example.useremployee23v2.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void testAtLeastOneJens() {
        List<Employee> lst = employeeRepository.findEmployeeByName("Jens");
        assertTrue(lst.size()>0);
    }

}