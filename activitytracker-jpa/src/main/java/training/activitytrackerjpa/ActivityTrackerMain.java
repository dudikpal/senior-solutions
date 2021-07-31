package training.activitytrackerjpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class ActivityTrackerMain {

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");

    public static void main(String[] args) {

        ActivityTrackerMain atm = new ActivityTrackerMain();
        Activity a1 = new Activity(LocalDateTime.of(2000, 1, 1, 1, 1), "description of a1", ActivityType.BASKETBALL);
        Activity a2 = new Activity(LocalDateTime.of(2000, 1, 2, 1, 1), "description of a2", ActivityType.BIKING);
        Activity a3 = new Activity(LocalDateTime.of(2000, 2, 1, 2, 2), "description of a3", ActivityType.HIKING);
        Activity a4 = new Activity(LocalDateTime.of(2000, 3, 1, 3, 3), "description of a4", ActivityType.RUNNING);
        Activity a5 = new Activity(LocalDateTime.of(2001, 3, 1, 3, 3), "description of a4", ActivityType.RUNNING);
        atm.createActivity(a1);
        atm.createActivity(a2);
        atm.createActivity(a3);
        atm.createActivity(a4);
        atm.createActivity(a5);

    }

    public void createActivity(Activity activity) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(activity);
        em.getTransaction().commit();
        em.close();
    }
}
