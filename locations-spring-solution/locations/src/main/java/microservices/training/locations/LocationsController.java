package microservices.training.locations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
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
                .map(Location::toString)
                .collect(Collectors.joining("<br>"));
    }
}
