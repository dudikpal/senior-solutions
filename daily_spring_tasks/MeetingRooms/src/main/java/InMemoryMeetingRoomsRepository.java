import java.util.*;
import java.util.stream.Collectors;

public class InMemoryMeetingRoomsRepository implements MeetingRoomsRepository {

    private List<MeetingRoom> meetingRooms = new ArrayList<>();

    @Override
    public void save(MeetingRoom meetingRoom) {
        meetingRooms.add(meetingRoom);
    }

    @Override
    public List<MeetingRoom> listAll() {
        return new ArrayList<>(meetingRooms);
    }

    @Override
    public void deleteAll() {
        meetingRooms.clear();
    }

    @Override
    public List<MeetingRoom> areaMoreThan(int minArea) {
        List<MeetingRoom> result = new ArrayList<>();
        for (MeetingRoom room : meetingRooms) {
            if (room.getArea() > minArea) {
                result.add(room);
            }
        }
        return result;
    }

    @Override
    public Optional<String> findByPartOfName(String partName) {
        List<MeetingRoom> result = new ArrayList<>();
        for (MeetingRoom meetingRoom : meetingRooms) {
            if (meetingRoom.getName().contains(partName)) {
                result.add(meetingRoom);
            }
        }
        if (result.size() == 0) {
            return Optional.empty();
        }
        result.sort(Comparator.comparing(MeetingRoom::getName));
        return Optional.of(result.toString());
    }

    @Override
    public Optional<String> findByFullName(String roomName) {
        Optional<MeetingRoom> result = listAll().stream()
                .filter(x -> x.getName().equals(roomName))
                .findFirst();
        if (result.isPresent()) {
            return Optional.of("Width: " + result.get().getWidth() +
                    "Length: " + result.get().getLength() +
                    "Area: " + result.get().getArea());
        }
        return Optional.empty();
    }

    @Override
    public List<String> roomsByArea() {
        return listAll().stream()
                .sorted(Comparator.comparing(MeetingRoom::getArea).reversed())
                .map(MeetingRoom::toString)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> everySecondRooms() {
        return listAll().stream()
                .map(MeetingRoom::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> reverseSortedRoomNames() {
        return meetingRooms.stream()
                .map(MeetingRoom::getName)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> sortedRoomNames() {
        return meetingRooms.stream()
                .map(MeetingRoom::getName)
                .sorted()
                .collect(Collectors.toList());
    }


}
