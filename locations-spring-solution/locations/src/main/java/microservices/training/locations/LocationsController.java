package microservices.training.locations;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LocationsController {

    private List<Location> locations = new ArrayList<>(List.of(
            new Location(1L, "name1", 2.22, 3.33),
            new Location(2L, "name2", 5.55, 4.44)
    ));

    @GetMapping("/")
    public List<Location> getLocations() {
        return locations;
    }
}
