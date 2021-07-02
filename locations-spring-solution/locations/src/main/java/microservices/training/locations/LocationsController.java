package microservices.training.locations;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/locations")
public class LocationsController {

    private LocationsService locationsService;

    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }

    @GetMapping
    public List<LocationDto> getLocations(@RequestParam Optional<String> name,
                                          @RequestParam Optional<Double> minLat,
                                          @RequestParam Optional<Double> minLon,
                                          @RequestParam Optional<Double> maxLat,
                                          @RequestParam Optional<Double> maxLon) {
        return locationsService.getLocations(name, minLat, minLon, maxLat, maxLon);
    }

    @GetMapping("/{id}")
    public LocationDto findById(@PathVariable("id") long id) {
        return locationsService.findById(id);
    }

}
