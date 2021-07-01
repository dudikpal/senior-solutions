package microservices.training.locations;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Collectors;

@RestController
public class LocationsController {

    private LocationsService locationsService;


    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }


    @GetMapping("/")
    public String getLocations() {
        return locationsService.getLocations().stream()
                .map(LocationDto::toString)
                .collect(Collectors.joining("<br>"));
    }
}
