import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MeetingRoomTest {


    @Test
    void test() {
        MeetingRoom meetingRoom = new MeetingRoom("elso", 4, 8);
        String expectedName = "elso";
        int expectedArea = 32;
        assertEquals(expectedName, meetingRoom.getName());
        assertEquals(expectedArea, meetingRoom.getArea());

    }

}