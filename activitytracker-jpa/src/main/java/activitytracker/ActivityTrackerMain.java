package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class ActivityTrackerMain {

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");

    public static void main(String[] args) {

        ActivityTrackerMain atm = new ActivityTrackerMain();
        EntityManager em = atm.factory.createEntityManager();


        atm.createActivity(new Activity(LocalDateTime.of(2000, 1, 1, 1, 1), "description of a1", ActivityType.BASKETBALL));
        atm.createActivity(new Activity(LocalDateTime.of(2000, 1, 2, 1, 1), "description of a2", ActivityType.BIKING));
        atm.createActivity(new Activity(LocalDateTime.of(2002, 2, 1, 2, 2), "description of a3", ActivityType.HIKING));
        atm.createActivity(new Activity(LocalDateTime.of(2000, 3, 1, 3, 3), "description of a4", ActivityType.RUNNING));
        atm.createActivity(new Activity(LocalDateTime.of(2001, 3, 1, 3, 3), "description of a5", ActivityType.RUNNING));

        List<Activity> activities = em.createQuery("select a from Activity a").getResultList();
        em.close();
        System.out.println(activities);

        em = atm.factory.createEntityManager();
        Activity activity = em.find(Activity.class, 3L);
        System.out.println(activity);

        em = atm.factory.createEntityManager();
        activity = em.find(Activity.class, 1L);
        activity.setDescription("New desc to a1");
        System.out.println(em.find(Activity.class, 1L).getDescription());

        em = atm.factory.createEntityManager();
        activity = em.find(Activity.class, 1L);
        em.getTransaction().begin();
        em.remove(activity);
        em.getTransaction().commit();
        System.out.println(em.createQuery("select a from Activity a").getResultList());
        em.close();

        atm.factory.close();
    }

    public void createActivity(Activity activity) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(activity);
        em.getTransaction().commit();
        em.close();
    }
}
