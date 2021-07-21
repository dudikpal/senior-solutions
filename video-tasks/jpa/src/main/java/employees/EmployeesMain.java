package employees;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmployeesMain {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Employee("John Doe"));
        em.persist(new Employee("Jack Doe"));
        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
