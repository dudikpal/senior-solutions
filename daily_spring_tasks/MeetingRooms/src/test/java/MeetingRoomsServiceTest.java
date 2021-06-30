import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MeetingRoomsServiceTest {

    private MeetingRoomsService meetingRoomsService = new MeetingRoomsService(new MariadbMeetingRoomsRepository());

    @BeforeEach
    void init() {
        meetingRoomsService.deleteAll();
    }

    @Test
    void testSaveThenList() {

        meetingRoomsService.save(new MeetingRoom("John Doe", 11, 11));

        List<MeetingRoom> meetingRooms = meetingRoomsService.listMeetingRooms();
        assertEquals(1, meetingRooms.size());
        assertEquals("John Doe", meetingRooms.get(0).getName());
    }

    @Test
    void testSaveTwoThenList() {

        meetingRoomsService.save(new MeetingRoom("John Doe", 11, 11));
        meetingRoomsService.save(new MeetingRoom("Jan Doe", 7, 7));

        List<MeetingRoom> meetingRooms = meetingRoomsService.listMeetingRooms();
        assertEquals(2, meetingRooms.size());
        assertEquals("Jan Doe", meetingRooms.get(1).getName());
    }

    @Test
    void testSaveWithMeetingsThenList() {
        meetingRoomsService.deleteAll();
        /*MeetingRoom meetingRoom = new MeetingRoom("Jan Doe", 11, 11);

        meetingRoom.addMeeting(new Meeting(LocalDateTime.of(2000, 1, 1, 10, 00),
                LocalDateTime.of(2000, 1, 1, 10, 50)));

        meetingRoom.addMeeting(new Meeting(LocalDateTime.of(2000, 1, 1, 11, 00),
                LocalDateTime.of(2000, 1, 1, 11, 50)));*/


        meetingRoomsService.save(new MeetingRoom("meeting2", 3, 4));
        meetingRoomsService.save(new MeetingRoom("Jan Doe", 11, 11, (List.of(
                new Meeting(LocalDateTime.of(2000, 1, 1, 12, 00),
                        LocalDateTime.of(2000, 1, 1, 12, 50)),
                new Meeting(LocalDateTime.of(2000, 1, 1, 13, 00),
                        LocalDateTime.of(2000, 1, 1, 13, 50))
        ))));
        List<MeetingRoom> meetingRooms = meetingRoomsService.listMeetingRooms();


        assertEquals(2, meetingRooms.size());
        assertEquals("Jan Doe", meetingRooms.get(1).getName());
        //assertEquals(LocalDateTime.of(2000, 1, 1, 11, 00), meetingRooms.get(0).getMeetings().get(1).getStartMeeting());
        System.out.println(meetingRooms);
    }


}