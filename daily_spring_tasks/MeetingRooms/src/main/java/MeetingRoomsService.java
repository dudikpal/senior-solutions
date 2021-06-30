import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MeetingRoomsService {

    private MeetingRoomsRepository meetingRoomsRepository;

    public MeetingRoomsService(MeetingRoomsRepository meetingRoomsRepository) {
        this.meetingRoomsRepository = meetingRoomsRepository;
    }

    public void save(MeetingRoom meetingRoom) {
        meetingRoomsRepository.save(meetingRoom);
    }

    public List<MeetingRoom> listMeetingRooms() {
        return meetingRoomsRepository.listAll();
    }

    public List<String> sortedRoomNames() {
        return meetingRoomsRepository.sortedRoomNames();
    }

    public List<String> reverseSortedRoomNames() {
        return meetingRoomsRepository.reverseSortedRoomNames();
    }

    public List<String> everySecondRooms() {
        List<String> names = meetingRoomsRepository.everySecondRooms();
        return IntStream.range(0, names.size())
                .filter(x -> x % 2 == 0)
                .mapToObj(names::get)
                .collect(Collectors.toList());
    }

    public List<String> roomsByArea() {
        return meetingRoomsRepository.roomsByArea();
    }

    public Optional<String> findByFullName(String roomName) {
        return meetingRoomsRepository.findByFullName(roomName);
    }

    public Optional<String> findByPartOfName(String partName) {
        return meetingRoomsRepository.findByPartOfName(partName);
    }

    public List<MeetingRoom> areaMoreThan(int minArea) {
        return meetingRoomsRepository.areaMoreThan(minArea);
    }

    public void deleteAll() {
        meetingRoomsRepository.deleteAll();
    }
}
