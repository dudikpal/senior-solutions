package locations;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LocationsController {

    private List<locations.Location> locations = new ArrayList<>(List.of(
            new Location(1, "name1", 2.22, 3.33),
            new Location(2, "name2", 4.44, 5.55)
    ));


    @GetMapping("/")
    @ResponseBody
    public String getLocations() {
        return locations.stream()
                .map(Location::toString)
                .collect(Collectors.joining("<br>"));
    }
}
