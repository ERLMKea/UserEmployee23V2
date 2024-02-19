package com.example.useremployee23v2.config;

import com.example.useremployee23v2.model.Employee;
import com.example.useremployee23v2.model.Gender;
import com.example.useremployee23v2.model.User;
import com.example.useremployee23v2.repository.EmployeeRepository;
import com.example.useremployee23v2.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        if (userRepository.findAll().size()>0) return;

        try {
            User user1 = new User();
            user1.setEmail("em@em.dk");
            user1.setPassword("abac");
            //userRepository.save(user1);

            Employee emp1 = new Employee();
            emp1.setBorn(LocalDateTime.now());
            emp1.setName("Jens");
            emp1.setGender(Gender.MALE);
            emp1.setVegetarian(false);
            emp1.setUser(user1);
            user1.setEmployee(emp1);
            userRepository.save(user1);
            employeeRepository.save(emp1);

            //employeeRepository.delete(emp1);
            //userRepository.delete(user1);
            //userRepository.delete(user1);
        } catch (DataAccessException e) {
            System.out.println("XXXXXXXXX Fejl i InitData");
            System.out.println(e.getMessage());
        }

    }
}
