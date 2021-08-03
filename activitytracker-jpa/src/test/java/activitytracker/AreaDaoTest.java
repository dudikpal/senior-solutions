package activitytracker;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AreaDaoTest {

    private ActivityDao activityDao;

    private AreaDao areaDao;

    @BeforeEach
    void setUp() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        areaDao = new AreaDao(factory);
        activityDao = new ActivityDao(factory);
    }


    @Test
    void testSaveAreas() {
        Area area1 = new Area("area1");
        Area area2 = new Area("area2");
        Area area3 = new Area("area3");
        areaDao.saveArea(area1);
        areaDao.saveArea(area2);
        areaDao.saveArea(area3);

        Activity activity1 = new Activity(LocalDateTime.of(2000, 1, 1, 1, 1), "description", ActivityType.BIKING);
        Activity activity2 = new Activity(LocalDateTime.of(2001, 1, 1, 1, 1), "description", ActivityType.BIKING);

        activity1.addArea(area1);
        activity1.addArea(area2);
        activity1.addArea(area3);
        activity2.addArea(area1);
        activity2.addArea(area2);

        activityDao.saveActivity(activity1);
        activityDao.saveActivity(activity2);

        Activity activity = activityDao.findActivityByIdWithAreas(activity1.getId());

        assertEquals(List.of("area1", "area2", "area3"),
                activity.getAreas().stream()
                        .map(Area::getName)
                        .collect(Collectors.toList()));
    }

    @Test
    void test_list_all_with_cities() {
        Area area = new Area("area1");
        area.addCity(new City("city1Name", 2500));
        area.addCity(new City("city2Name", 3500));
        area.addCity(new City("city3Name", 4500));

        areaDao.saveArea(area);
        long id = area.getId();

        Area another = areaDao.findAreaByIdWithCities(id);
        assertEquals(3500, another.getCities().get("city2Name").getPopulation());

    }

}