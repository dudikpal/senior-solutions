package training.jpaspringboot;


import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EmployeeDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveEmployee(Employee employee) {
        entityManager.persist(employee);
    }

    public List<Employee> listAllEmployees() {
        return entityManager.createQuery("select e from Employee e")
                .getResultList();
    }

    public Employee findEmployeeByName(String name) {
        return entityManager.createQuery("select e from Employee e where e.name = :name",
                Employee.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
