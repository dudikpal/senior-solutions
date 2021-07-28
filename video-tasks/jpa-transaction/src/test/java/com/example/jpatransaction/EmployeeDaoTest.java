package com.example.jpatransaction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeDaoTest {

    @Autowired
    private EmployeeDao employeeDao;


    @Test
    void testSaveThenFind() {
        Employee employee = new Employee("John Doe");
        employeeDao.saveEmployeeById(employee);

        long id = employee.getId();

        assertEquals("John Doe", employeeDao.findEmployeeById(id).getName());
    }

    @Test
    void testSaveWithEmptyName() {
        Employee employee = new Employee("");

        assertThrows(IllegalArgumentException.class, () ->
                employeeDao.saveEmployeeById(employee));

    }

}