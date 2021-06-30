import java.util.List;
import java.util.Optional;

public interface MeetingRoomsRepository {

    void save(MeetingRoom meetingRoom);

    List<MeetingRoom> listAll();

    List<String> sortedRoomNames();

    List<String> reverseSortedRoomNames();

    List<String> everySecondRooms();

    List<String> roomsByArea();

    Optional<String> findByFullName(String roomName);

    Optional<String> findByPartOfName(String partName);

    List<MeetingRoom> areaMoreThan(int minArea);

    void deleteAll();
}
