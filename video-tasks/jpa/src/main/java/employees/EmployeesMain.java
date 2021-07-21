package employees;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesMain {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Employee employee = new Employee("John Doe");
        Employee employee2 = new Employee("Jack Doe");
        em.persist(employee);
        em.persist(employee2);
        em.getTransaction().commit();
        long id = employee2.getId();
        em = factory.createEntityManager();
        employee = em.find(Employee.class, id);
        System.out.println(employee);

        em.getTransaction().begin();
        employee = em.find(Employee.class, id);
        employee.setName("JJJ Doe");
        em.getTransaction().commit();

        List<Employee> employees = em.createQuery("select e from Employee e", Employee.class)
                .getResultList();
        System.out.println(employees);

        em.getTransaction().begin();
        employee = em.find(Employee.class, id);
        em.remove(employee);
        em.getTransaction().commit();

        employees = em.createQuery("select e from Employee e", Employee.class).getResultList();
        System.out.println(employees);

        em.close();
        factory.close();
    }
}
