package activitytracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDate;
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

    /*@Test
    void test_list_all_with_labels() {
        Activity a1 = new Activity(LocalDateTime.of(2000, 1, 1, 1, 1), "description of a1", ActivityType.BASKETBALL);
        a1.setLabels(List.of("label1 a1", "label2 a1"));

        activityDao.saveActivity(a1);

        Activity another1 = activityDao.findActivityByIdWithLabels(a1.getId());

        assertThat(another1.getLabels())
                .hasSize(2)
                //.extracting(String::new)
                .containsExactly("label1 a1", "label2 a1");

    }*/

    @Test
    void test_list_all_with_trackpoints() {
        Activity activity = new Activity(LocalDateTime.of(2000, 1, 1, 1, 1), "description text", ActivityType.BIKING);
        activity.addTrackPoint(new TrackPoint(LocalDate.of(2000, 2, 1), 2.22, 1.11));
        activity.addTrackPoint(new TrackPoint(LocalDate.of(2000, 1, 2), 3.22, 2.11));
        activity.addTrackPoint(new TrackPoint(LocalDate.of(2000, 1, 1), 4.22, 3.11));

        activityDao.saveActivity(activity);

        long id = activity.getId();

        Activity another = activityDao.findActivityByIdWithTrackPoints(id);

        assertThat(another.getTrackPoints())
                .hasSize(3)
                .extracting(TrackPoint::getLat)
                .containsExactly(4.22, 3.22, 2.22);

    }

    @Test
    void test_Activity_Details() {
        Activity activity = new Activity(LocalDateTime.of(2000, 1, 1, 1, 1), "description", ActivityType.HIKING);
        activity.setDistance(1000);
        activity.setDuration(100);

        activityDao.saveActivity(activity);

        Activity another = activityDao.findActivityById(activity.getId());

        assertEquals(1000, another.getDistance());

    }

    @Test
    void test_list_trackpoints_after_date() {
        Activity a1 = new Activity(LocalDateTime.of(2017, 1, 1, 1, 1), "desc a1", ActivityType.BIKING);
        Activity a2 = new Activity(LocalDateTime.of(2019, 1, 1, 1, 1), "desc a1", ActivityType.BIKING);
        Activity a3 = new Activity(LocalDateTime.of(2018, 2, 1, 1, 1), "desc a1", ActivityType.BIKING);
        Activity a4 = new Activity(LocalDateTime.of(2018, 2, 1, 1, 1), "desc a1", ActivityType.BIKING);
        Activity a5 = new Activity(LocalDateTime.of(2018, 2, 1, 1, 1), "desc a1", ActivityType.BIKING);

        a1.addTrackPoint(new TrackPoint(LocalDate.of(2000, 1, 1), 1.11, 2.22));
        a2.addTrackPoint(new TrackPoint(LocalDate.of(2000, 1, 1), 3.11, 4.22));
        a3.addTrackPoint(new TrackPoint(LocalDate.of(2000, 1, 1), 5.11, 6.22));
        a4.addTrackPoint(new TrackPoint(LocalDate.of(2000, 1, 1), 7.11, 8.22));
        a5.addTrackPoint(new TrackPoint(LocalDate.of(2000, 1, 1), 9.11, 16.22));

        activityDao.saveActivity(a1);
        activityDao.saveActivity(a2);
        activityDao.saveActivity(a3);
        activityDao.saveActivity(a4);
        activityDao.saveActivity(a5);

        List<CoordinateDto> coordinates = activityDao.findTrackPointCoordinatesByDate(LocalDateTime.of(2018, 01, 01, 0, 0), 2, 2);
        System.out.println();
        assertEquals(2, coordinates.size());
        assertEquals(9.11, coordinates.get(1).getLat(), 0.00005);
        assertEquals(16.22, coordinates.get(1).getLon(), 0.00005);
    }

    @Test
    void test_find_TrackPoint_Count_By_Activity() {
        Activity activity1 = new Activity(LocalDateTime.of(2000, 1, 1, 1, 1), "desc activity1", ActivityType.HIKING);
        Activity activity2 = new Activity(LocalDateTime.of(2000, 1, 1, 1, 1), "desc activity2", ActivityType.BIKING);
        TrackPoint t1 = new TrackPoint(LocalDate.of(2000, 1, 1), 1.11, 2.22);
        TrackPoint t2 = new TrackPoint(LocalDate.of(2000, 1, 1), 3.11, 4.22);
        TrackPoint t3 = new TrackPoint(LocalDate.of(2000, 1, 1), 5.11, 6.22);

        activity1.addTrackPoint(t1);
        activity1.addTrackPoint(t2);
        activity2.addTrackPoint(t3);

        activityDao.saveActivity(activity1);
        activityDao.saveActivity(activity2);

        List<Object[]> result = activityDao.findTrackPointCountByActivity();

        assertEquals(2, result.size());
        assertEquals("desc activity2", result.get(1)[0]);
    }

    @Test
    void remove_Activities_By_Date_And_Type() {
        activityDao.saveActivity(new Activity(LocalDateTime.of(2000, 1, 1, 1, 1), "desc a1", ActivityType.BIKING));
        activityDao.saveActivity(new Activity(LocalDateTime.of(2001, 1, 1, 1, 1), "desc a2", ActivityType.BIKING));
        activityDao.saveActivity(new Activity(LocalDateTime.of(2001, 1, 1, 1, 1), "desc a3", ActivityType.HIKING));
        activityDao.saveActivity(new Activity(LocalDateTime.of(2000, 1, 1, 1, 1), "desc a4", ActivityType.BIKING));
        activityDao.saveActivity(new Activity(LocalDateTime.of(2001, 1, 1, 1, 1), "desc a5", ActivityType.RUNNING));
        activityDao.saveActivity(new Activity(LocalDateTime.of(2001, 1, 1, 1, 1), "desc a6", ActivityType.BASKETBALL));

        activityDao.removeActivitiesByDateAndType(LocalDateTime.of(2000, 2, 2, 2, 2), ActivityType.BIKING);

        List<Activity> activities = activityDao.listActivities();

        assertEquals(5, activities.size());
    }

}