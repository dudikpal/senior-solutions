package activitytracker;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "activities")
@Data
@NoArgsConstructor
public class Activity {

    @TableGenerator(name = "Activity_Gen",
            table = "act_id_gen",
            pkColumnName = "id_gen",
            pkColumnValue = "id_val"
    )
    @Id
    @GeneratedValue(generator = "Activity_Gen")
    private Long id;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @Column(name = "type", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private ActivityType type;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /*@ElementCollection
    private List<String> labels = new ArrayList<>();*/

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "activity")
    @OrderBy("time")
    private List<TrackPoint> trackPoints;

    @ManyToMany
    private List<Area> areas = new ArrayList<>();

    public Activity(LocalDateTime startTime, String description, ActivityType type) {
        this.startTime = startTime;
        this.description = description;
        this.type = type;
    }

    @PrePersist
    public void initCreateAt() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void initUpdateAt() {
        updatedAt = LocalDateTime.now();
    }

    public void addTrackPoint(TrackPoint trackPoint) {
        if (trackPoints == null) {
            trackPoints = new ArrayList<>();
        }
        trackPoints.add(trackPoint);
        trackPoint.setActivity(this);
    }

    public void addArea(Area area) {
        areas.add(area);
        area.getActivities().add(this);
    }
}
