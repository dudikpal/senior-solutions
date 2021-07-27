package employees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ProjectDaoTest {

    private ProjectDao projectDao;
    private EmployeeDao employeeDao;

    @BeforeEach
    void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        projectDao = new ProjectDao(entityManagerFactory);
        employeeDao = new EmployeeDao(entityManagerFactory);
    }

    @Test
    void testSaveProject() {
        Employee e1 = new Employee("John Doe");
        Employee e2 = new Employee("Jane Doe");
        Employee e3 = new Employee("Jack Doe");

        employeeDao.save(e1);
        employeeDao.save(e2);
        employeeDao.save(e3);

        Project java = new Project("Java");
        Project dotNet = new Project("DotNet");
        Project python = new Project("Python");

        java.addEmployee(e1);
        java.addEmployee(e2);

        python.addEmployee(e1);
        python.addEmployee(e3);

        dotNet.addEmployee(e3);

        projectDao.saveProject(java);
        projectDao.saveProject(dotNet);
        projectDao.saveProject(python);

        Project project = projectDao.findProjectByName("Java");
        assertEquals(Set.of("John Doe", "Jane Doe"), project.getEmployees().stream()
        .map(Employee::getName)
        .collect(Collectors.toSet()));
    }


}