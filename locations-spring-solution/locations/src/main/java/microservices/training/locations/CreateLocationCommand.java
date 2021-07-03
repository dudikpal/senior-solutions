package microservices.training.locations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;*/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLocationCommand {

    /*@NotBlank(message = "Name must be not empty")*/
    private String name;

    /*@Max(value = 90, message = "Latitude must between 90 and -90")
    @Min(value = -90, message = "Latitude must between 90 and -90")*/
    private double lat;

    /*@Max(value = 180, message = "Longitude must between 180 and -180")
    @Min(value = -180, message = "Longitude must between 180 and -180")*/
    private double lon;
}
