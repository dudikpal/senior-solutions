import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDaoTest {

    private EmployeeDao employeeDao;

    @BeforeEach
    void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        employeeDao = new EmployeeDao(entityManagerFactory);
    }

    @Test
    void testSaveAndFind() {
        employeeDao.saveEmployee(new Employee("John Doe"));
        employeeDao.saveEmployee(new CompanyEmployee("Jane Doe", 22));
        employeeDao.saveEmployee(new ContractEmployee("Jak Doe", 100000));

        Employee employee = employeeDao.findEmployeeByName("John Doe");
        assertEquals("John Doe", employee.getName());


        Employee company = employeeDao.findEmployeeByName("Jane Doe");
        assertEquals("Jane Doe", company.getName());
        assertEquals(22, ((CompanyEmployee)company).getVacation());

        Employee contracted = employeeDao.findEmployeeByName("Jak Doe");
        assertEquals("Jak Doe", contracted.getName());
        assertEquals(100000, ((ContractEmployee)contracted).getDailyRate());
    }

}