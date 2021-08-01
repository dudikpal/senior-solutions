package activitytracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ActivityDaoTest {

    private EntityManagerFactory factory;
    private ActivityDao activityDao;


    @BeforeEach
    void setUp() {
        factory = Persistence.createEntityManagerFactory("pu");
        activityDao = new ActivityDao(factory);
    }


    @Test
    void test_Save_Than_Find_By_Id() {
        Activity activity = new Activity(LocalDateTime.of(2000, 1, 1, 1, 1), "description of a1", ActivityType.BASKETBALL);
        activityDao.saveActivity(activity);
        long id = activity.getId();

        Activity another = activityDao.findActivityById(id);
        assertEquals(activity.getDescription(), another.getDescription());
    }

    @Test
    void test_Save_Than_List_All() {
        activityDao.saveActivity(new Activity(LocalDateTime.of(2000, 1, 1, 1, 1), "description of a1", ActivityType.BASKETBALL));
        activityDao.saveActivity(new Activity(LocalDateTime.of(2000, 1, 2, 1, 1), "description of a2", ActivityType.BIKING));
        activityDao.saveActivity(new Activity(LocalDateTime.of(2002, 2, 1, 2, 2), "description of a3", ActivityType.HIKING));
        activityDao.saveActivity(new Activity(LocalDateTime.of(2000, 3, 1, 3, 3), "description of a4", ActivityType.RUNNING));
        activityDao.saveActivity(new Activity(LocalDateTime.of(2001, 3, 1, 3, 3), "description of a5", ActivityType.RUNNING));

        List<Activity> activities = activityDao.listActivities();
        assertThat(activities)
                .hasSize(5)
                .extracting(Activity::getType)
                .containsAll(Set.of(
                        ActivityType.BASKETBALL,
                        ActivityType.BIKING,
                        ActivityType.HIKING,
                        ActivityType.RUNNING
                ));
    }

    @Test
    void test_delete_activity() {
        activityDao.saveActivity(new Activity(LocalDateTime.of(2000, 1, 1, 1, 1), "description of a1", ActivityType.BASKETBALL));
        activityDao.saveActivity(new Activity(LocalDateTime.of(2000, 1, 2, 1, 1), "description of a2", ActivityType.BIKING));
        activityDao.saveActivity(new Activity(LocalDateTime.of(2002, 2, 1, 2, 2), "description of a3", ActivityType.HIKING));

        Activity activity = activityDao.findActivityById(1L);
        activityDao.deleteActivity(activity.getId());

        List<Activity> activities = activityDao.listActivities();
        assertEquals(2, activities.size());
    }

    @Test
    void test_update_activity_description() {
        activityDao.saveActivity(new Activity(LocalDateTime.of(2000, 1, 1, 1, 1), "description of a1", ActivityType.BASKETBALL));

        Activity activity = activityDao.findActivityById(1L);

        String desc = "New description";
        activityDao.updateActivity(1, desc);

        Activity another = activityDao.findActivityById(1L);
        assertEquals(desc, another.getDescription());
    }

    @Test
    void test_list_all_with_labels() {
        Activity a1 = new Activity(LocalDateTime.of(2000, 1, 1, 1, 1), "description of a1", ActivityType.BASKETBALL);
        a1.setLabels(List.of("label1 a1", "label2 a1"));

        activityDao.saveActivity(a1);

        Activity another1 = activityDao.findActivityByIdWithLabels(a1.getId());

        assertThat(another1.getLabels())
                .hasSize(2)
                //.extracting(String::new)
                .containsExactly("label1 a1", "label2 a1");

    }
}