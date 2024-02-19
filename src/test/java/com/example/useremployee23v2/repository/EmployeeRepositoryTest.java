package com.example.useremployee23v2.repository;

import com.example.useremployee23v2.model.Employee;
import jakarta.transaction.Transactional;
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


    @Test
    void testDeleteJens() {
        List<Employee> lst = employeeRepository.findEmployeeByName("Jens");
        if (lst.size()>0) {
            try {
                System.out.println("delet"+lst.get(0).getName());
                employeeRepository.delete(lst.get(0));

            } catch (Exception e) {
                System.out.println("yyyyyyy" + e.getMessage());
            }

        }
        lst = employeeRepository.findEmployeeByName("Jens");
        assertTrue(lst.size()==1); //der bliver ikke slettet en employee
    }

}