package com.example.useremployee23v2.repository;

import com.example.useremployee23v2.model.Employee;
import com.example.useremployee23v2.model.Gender;
import com.example.useremployee23v2.model.User;
import jakarta.transaction.Transactional;
import org.hibernate.TransientPropertyValueException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class NoCascadeAllSaveTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void testSaveUserAndEmployee() {
        //Tester at user kan gemmes når ikke employee er sat i user. Derefter employee
        List<Employee> lstEmp = employeeRepository.findAll();
        int startSz = lstEmp.size();
        try {
            User user1 = new User();
            user1.setEmail("em" + startSz+1 + "@em2.dk");
            user1.setPassword("abac");
            userRepository.save(user1);
            Employee emp1 = new Employee();
            emp1.setBorn(LocalDateTime.now());
            emp1.setName("Kurt2");
            emp1.setGender(Gender.MALE);
            emp1.setVegetarian(false);
            emp1.setUser(user1);
            user1.setEmployee(emp1);
            //userRepository.save(user1);
            employeeRepository.save(emp1);
        } catch (DataAccessException e) {
            System.out.println("XXXXXXXXX Fejl i Test metode");
            System.out.println(e.getMessage());
        }
        lstEmp = employeeRepository.findAll();
        //Det her virker uden CascadeAll. der bliver gemt en user og en employee
        assertTrue(lstEmp.size() > startSz);
    }

    @Test
    void testSaveUserAndEmployee2() {
        //Tester at save user fejler fordi prøver save employee i samme transaction.
        List<Employee> lstEmp = employeeRepository.findAll();
        int startSz = lstEmp.size();
            User user1 = new User();
            user1.setEmail("em" + startSz+1 + "@em2.dk");
            user1.setPassword("abac");
            //userRepository.save(user1); Det her virker fordi ikke sat employee
            Employee emp1 = new Employee();
            emp1.setBorn(LocalDateTime.now());
            emp1.setName("Kurt2");
            emp1.setGender(Gender.MALE);
            emp1.setVegetarian(false);
            emp1.setUser(user1);
            user1.setEmployee(emp1);
            //userRepository.save(user1); Det her virker ikke fordi prøver save employee i samme transaction.
            assertThrows(InvalidDataAccessApiUsageException.class, () -> userRepository.save(user1));
    }

    @Test
    @Transactional
    void testSaveUserAndEmployee3() {
        //Virker stadig ikke selvom annoteret med Transactional
        List<Employee> lstEmp = employeeRepository.findAll();
        int startSz = lstEmp.size();
        User user1 = new User();
        user1.setEmail("em" + startSz+1 + "@em2.dk");
        user1.setPassword("abac");
        Employee emp1 = new Employee();
        emp1.setBorn(LocalDateTime.now());
        emp1.setName("Kurt33");
        emp1.setGender(Gender.MALE);
        emp1.setVegetarian(false);
        emp1.setUser(user1);
        user1.setEmployee(emp1);
        System.out.println("Insert both user and employee");
        //userRepository.save(user1); //Det her virker ikke fordi prøver save employee i samme transaction.
        //kan ikke få det her til at virke
        assertThrows(InvalidDataAccessApiUsageException.class, () -> userRepository.save(user1));
        lstEmp = employeeRepository.findAll();
        int endSz = lstEmp.size();
        System.out.println("finished" + endSz);
    }

    @Test
    @Transactional
    void testSaveUserAndEmployee4() {
        //Tester save user virker, hvor vi gemmer employee i stedet for user, fordi annoteret Transactional
        //Virker heller ikke
        List<Employee> lstEmp = employeeRepository.findAll();
        int startSz = lstEmp.size();
        User user1 = new User();
        user1.setEmail("em" + startSz+1 + "@em2.dk");
        user1.setPassword("abac");
        Employee emp1 = new Employee();
        emp1.setBorn(LocalDateTime.now());
        emp1.setName("Kurt33");
        emp1.setGender(Gender.MALE);
        emp1.setVegetarian(false);
        emp1.setUser(user1);
        user1.setEmployee(emp1);
        //Det her virker fordi sat Transactional
        System.out.println("Insert both user and employee");
        assertThrows(InvalidDataAccessApiUsageException.class, () -> employeeRepository.save(emp1));
    }


}
