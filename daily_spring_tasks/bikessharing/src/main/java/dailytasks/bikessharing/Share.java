package dailytasks.bikessharing;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Share {

    @Schema(description = "ID of bike", example = "eyayID")
    private String bikeId;
    private String userId;
    private LocalDateTime dateTime;
    private double km;

    public Share(String bikeId, String userId, LocalDateTime dateTime, double km) {
        this.bikeId = bikeId;
        this.userId = userId;
        this.dateTime = dateTime;
        this.km = km;
    }

    public String getBikeId() {
        return bikeId;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public double getKm() {
        return km;
    }

    @Override
    public String toString() {
        return "Share{" +
                "bikeId='" + bikeId + '\'' +
                ", userId='" + userId + '\'' +
                ", dateTime=" + dateTime +
                ", km=" + km +
                '}';
    }
}
