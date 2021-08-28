package nav;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class Interval {

    private LocalDateTime start;

    private LocalDateTime end;

    public Interval(LocalDateTime start, LocalDateTime end) {
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("Invalid start and end");
        }
        this.start = start;
        this.end = end;
    }
}
