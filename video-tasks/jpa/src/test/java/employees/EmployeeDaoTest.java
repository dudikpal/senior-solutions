package employees;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeDaoTest {

    private EntityManagerFactory entityManagerFactory;
    private EmployeeDao employeeDao;

    @BeforeEach
    void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        employeeDao = new EmployeeDao(entityManagerFactory);
    }

    @Test
    void testSaveThenFindById() {
        Employee employee = new Employee("John Dao");
        employeeDao.save(employee);
        long id = employee.getId();
        Employee another = employeeDao.findById(id);
        assertEquals("John Dao", another.getName());
    }

    @Test
    void testSaveThanListAll() {
        employeeDao.save(new Employee("Ja Doe"));
        employeeDao.save(new Employee("Jo Doe"));

        List<Employee> employees = employeeDao.listAll();
        assertEquals(List.of("Ja Doe", "Jo Doe"), employees.stream()
        .map(Employee::getName).collect(Collectors.toList()));
    }

    @Test
    void testChangeName() {
        Employee employee = new Employee("John Dao");
        employeeDao.save(employee);
        long id = employee.getId();
        employeeDao.changeName(id, "Joco Doe");
        Employee another = employeeDao.findById(id);
        assertEquals("Joco Doe", another.getName());
    }

    @Test
    void testDelete() {
        Employee employee = new Employee("Johnd Sao");
        employeeDao.save(employee);
        long id = employee.getId();
        employeeDao.delete(id);

        List<Employee> employees = employeeDao.listAll();
        assertTrue(employees.isEmpty());
    }

    @Test
    void testEmployeeWithAttributes() {
        employeeDao.save(new Employee("jo Doe", Employee.EmployeeType.HALF_TIME, LocalDate.of(2000, 1, 1)));
        Employee employee = employeeDao.listAll().get(0);
        System.out.println(employee);
        assertEquals(LocalDate.of(2000, 1, 1), employee.getDateOfBirth());
    }

    @Test
    void testEmployeeWithAttributesId() {
        for (int i = 0; i < 10; i++) {
            employeeDao.save(new Employee("John Doe", Employee.EmployeeType.HALF_TIME,
                    LocalDate.of(2000, 1, 1)));
        }
        Employee employee = employeeDao.listAll().get(0);
        assertEquals(LocalDate.of(2000, 1, 1), employee.getDateOfBirth());
    }

}