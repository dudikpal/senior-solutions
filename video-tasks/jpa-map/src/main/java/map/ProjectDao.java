package map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ProjectDao {

    private EntityManagerFactory factory;

    public ProjectDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void saveProject(Project project) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(project);
        em.getTransaction().commit();
        em.close();
    }

    public Project findById(Long id) {
        EntityManager em = factory.createEntityManager();
        Project project = em.createQuery("select p from Project p join fetch p.employees where p.id = :id",
                Project.class)
                .setParameter("id", id)
                .getSingleResult();
        return project;
    }
}
