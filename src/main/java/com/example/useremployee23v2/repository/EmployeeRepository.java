package com.example.useremployee23v2.repository;

import com.example.useremployee23v2.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findEmployeeByName(String name);

}
