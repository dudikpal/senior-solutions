package map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class ProjectDaoTest {

    private ProjectDao projectDao;

    @BeforeEach
    void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        projectDao = new ProjectDao(entityManagerFactory);
    }

    @Test
    void testSaveThanFind() {
        Project project = new Project("Java");
        project.getEmployees().put("java_1", new Employee("c123", "John Doe"));
        project.getEmployees().put("java_2", new Employee("c456", "Jane Doe"));

        projectDao.saveProject(project);

        Project another = projectDao.findById(project.getId());
        assertEquals("John Doe", another.getEmployees().get("java_1").getName());
    }

}