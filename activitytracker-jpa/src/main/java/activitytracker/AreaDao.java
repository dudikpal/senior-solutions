package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class AreaDao {

    private EntityManagerFactory factory;

    public AreaDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void saveArea(Area area) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(area);
        em.getTransaction().commit();
        em.close();
    }

    public Area findById(long id) {
        EntityManager em = factory.createEntityManager();
        Area area = em.createQuery("select a from Area a where a.id = :id",
                Area.class)
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return area;
    }

    public Area findAreaByIdWithCities(long id) {
        EntityManager em = factory.createEntityManager();
        Area area = em.createQuery("select a from Area a join fetch a.cities where a.id = :id",
                Area.class)
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return area;
    }
}
