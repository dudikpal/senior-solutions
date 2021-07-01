package microservices.training.locations;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class LocationsService {

    private List<Location> locations = new ArrayList<>(List.of(
            new Location(1L, "name1", 2.22, 3.33),
            new Location(2L, "name2", 5.55, 4.44)
    ));


    public List<Location> getLocations() {
        return locations;
    }
}
