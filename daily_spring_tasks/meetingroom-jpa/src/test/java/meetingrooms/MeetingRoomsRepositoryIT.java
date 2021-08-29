package meetingrooms;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.TransactionScoped;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MeetingRoomsRepositoryIT {

    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");

    static MeetingRoomRepository repository = new MeetingRoomRepository(factory);


    @BeforeEach
    void emptyTable() {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("delete from MeetingRoom ").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @AfterAll
    static void closeFactory() {
        factory.close();
    }

    @Test
    @DisplayName("Save two meeting rooms than query")
    void testSaveThanList() {
        repository.save("Jupiter", 100, 20);
        repository.save("Neptunus", 100, 10);
        List<String> names = repository.getMeetingRoomsOrderedByName();
        assertEquals(List.of("Jupiter", "Neptunus"), names);
    }

    @Test
    @DisplayName("Save two meeting rooms than query")
    void testSaveThanList2() {
        repository.save("Jupiter", 100, 20);
        repository.save("Neptunus", 100, 10);
        List<String> names = repository.getMeetingRoomsOrderedByName();
        assertEquals(List.of("Jupiter", "Neptunus"), names);
    }

}