package activitytracker;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "trackpoints")
@Data
@NoArgsConstructor
public class TrackPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate time;

    @Column(name = "latitude")
    private double lat;

    @Column(name = "longitude")
    private double lon;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Activity activity;

    public TrackPoint(LocalDate time, double lat, double lon) {
        this.time = time;
        this.lat = lat;
        this.lon = lon;
    }
}
