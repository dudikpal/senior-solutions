import java.util.ArrayList;
import java.util.List;

public class MeetingRoom {

    private long id;
    private String name;
    private int width;
    private int length;
    private int area;
    private List<Meeting> meetings = new ArrayList<>();

    public MeetingRoom(String name) {
        this.name = name;
    }

    public MeetingRoom(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public MeetingRoom(String name, int width, int length) {
        this.name = name;
        this.width = width;
        this.length = length;
        area = width * length;
    }

    public MeetingRoom(long id, String name, int width, int length) {
        this.id = id;
        this.name = name;
        this.width = width;
        this.length = length;
    }

    public MeetingRoom(String name, int width, int length, List<Meeting> meetings) {
        this.name = name;
        this.width = width;
        this.length = length;
        this.meetings = meetings;
    }

    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    public void addMeetingList(List<Meeting> meetingList) {
        meetings.addAll(meetingList);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getArea() {
        return area;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    @Override
    public String toString() {
        return "\nMeetingRoom{" +
                ", name='" + name + '\'' +
                ", width=" + width +
                ", length=" + length +
                ", area=" + area +
                "\nmeetings:\n" + meetings +
                "}\n";
    }
}
