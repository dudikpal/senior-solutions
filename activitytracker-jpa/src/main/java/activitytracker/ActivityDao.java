package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ActivityDao {

    private EntityManagerFactory factory;

    public ActivityDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void saveActivity(Activity activity) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(activity);
        em.getTransaction().commit();
        em.close();
    }


    public List<Activity> listActivities() {
        EntityManager em = factory.createEntityManager();
        List<Activity> activities = em.createQuery("select a from Activity a").getResultList();
        em.close();
        return activities;
    }


    public Activity findActivityById(long id) {
        EntityManager em = factory.createEntityManager();
        Activity activity = em.find(Activity.class, id);
        em.close();
        return activity;
    }


    public void deleteActivity(long id) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Activity activity = em.find(Activity.class, id);
        em.remove(activity);
        em.getTransaction().commit();
        em.close();
    }


    public void updateActivity(long id, String description) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Activity activity = em.find(Activity.class, id);
        activity.setDescription(description);
        em.getTransaction().commit();
        em.close();
    }


    public Activity findActivityByIdWithLabels(long id) {
        EntityManager em = factory.createEntityManager();
        Activity activity = em.createQuery(
                "select a from Activity a join fetch a.labels where a.id = :id",
                Activity.class
        )
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return activity;
    }


    public Activity findActivityByIdWithTrackPoints(long id){
        EntityManager em = factory.createEntityManager();
        Activity activity = em
                .createQuery("select a from Activity a join fetch a.trackPoints where a.id = :id",
                        Activity.class)
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return activity;
    }


    public Activity findActivityByIdWithAreas(long id){
        EntityManager em = factory.createEntityManager();
        Activity activity = em
                .createQuery("select a from Activity a join fetch a.areas where a.id = :id",
                        Activity.class)
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return activity;
    }


    public List<CoordinateDto> findTrackPointCoordinatesByDate(LocalDateTime afterThis, int start, int limit) {
        EntityManager em = factory.createEntityManager();
        List<CoordinateDto> coordinates = em
                .createNamedQuery("listCoordinatesAfterDate", CoordinateDto.class)
                .setParameter("afterThis", afterThis)
                .setFirstResult(start)
                .setMaxResults(limit)
                .getResultList();
        em.close();
        return coordinates;
    }


    public List<Object[]> findTrackPointCountByActivity() {
        EntityManager em = factory.createEntityManager();
        List<Object[]> result = em
                .createQuery("select a.description, size(a.trackPoints) from Activity a join a.trackPoints group by a.description",
                        Object[].class)
                .getResultList();
        em.close();
        return result;
    }
}
