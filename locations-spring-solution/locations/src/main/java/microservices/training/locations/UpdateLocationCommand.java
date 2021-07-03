package microservices.training.locations;

import lombok.Data;

@Data
public class UpdateLocationCommand {

    private String name;
    private Double lat;
    private Double lon;
}
