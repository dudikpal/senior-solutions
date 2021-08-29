package meetingrooms;


import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@AllArgsConstructor
public class MeetingRoomRepository {

    private EntityManagerFactory factory;

    MeetingRoom save(String name, int width, int length) {
        EntityManager em = factory.createEntityManager();
        MeetingRoom meetingRoom = new MeetingRoom(name, width, length);
        em.getTransaction().begin();
        em.persist(meetingRoom);
        em.getTransaction().commit();
        em.close();
        return meetingRoom;
    }

    List<String> getMeetingRoomsOrderedByName() {
        EntityManager em = factory.createEntityManager();
        List<String> names = em.createNamedQuery("getAllOrderByName",
                String.class)
                .getResultList();
        return names;
    }
}
