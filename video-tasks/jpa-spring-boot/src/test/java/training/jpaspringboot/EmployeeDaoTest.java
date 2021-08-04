package training.jpaspringboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(statements = "delete from employees")
class EmployeeDaoTest {

    @Autowired
    EmployeeDao employeeDao;

    @Test
    void testSaveAndFind() {
        employeeDao.saveEmployee(new Employee("John Doe"));

        Employee employee = employeeDao.findEmployeeByName("John Doe");
        assertEquals("John Doe", employee.getName());
    }

    @Test
    void testListAllEmployees() {
        employeeDao.saveEmployee(new Employee("John Doa"));
        employeeDao.saveEmployee(new Employee("John Dao"));

        List<Employee> employees = employeeDao.listAllEmployees();

        assertEquals(2, employees.size());
    }
}