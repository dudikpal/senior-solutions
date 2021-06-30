import java.time.LocalDateTime;

public class Meeting {

    private Long id;
    private LocalDateTime startMeeting;
    private LocalDateTime endMeeting;
    //private int roomId;

    public Meeting(LocalDateTime startMeeting, LocalDateTime endMeeting) {
        this.startMeeting = startMeeting;
        this.endMeeting = endMeeting;
        //this.roomId = roomId;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartMeeting() {
        return startMeeting;
    }

    public LocalDateTime getEndMeeting() {
        return endMeeting;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                ", startMeeting=" + startMeeting +
                ", endMeeting=" + endMeeting +
                "}\n";
    }
}
