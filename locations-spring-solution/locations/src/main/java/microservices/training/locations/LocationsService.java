package microservices.training.locations;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class LocationsService {

    private ModelMapper modelMapper;

    private AtomicLong id = new AtomicLong();

    private List<Location> locations = new ArrayList<>(List.of(
            new Location(id.incrementAndGet(), "name1", 1.11, 2.22),
            new Location(id.incrementAndGet(), "name2", 2.22, 3.33),
            new Location(id.incrementAndGet(), "name3", 4.44, 5.55)
    ));


    public LocationsService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public List<LocationDto> getLocations(Optional<String> name,
                                          Optional<Double> minLat,
                                          Optional<Double> minLon,
                                          Optional<Double> maxLat,
                                          Optional<Double> maxLon) {
        Type targetListType = new TypeToken<List<LocationDto>>(){}.getType();
        List<Location> filtered = locations.stream()
                .filter(loc -> name.isEmpty() || loc.getName().equalsIgnoreCase(name.get()))
                .filter(loc -> minLat.isEmpty() || loc.getLat() >= minLat.get())
                .filter(loc -> maxLat.isEmpty() || loc.getLat() <= maxLat.get())
                .filter(loc -> minLon.isEmpty() || loc.getLon() >= minLon.get())
                .filter(loc -> maxLon.isEmpty() || loc.getLon() <= maxLon.get())
                .collect(Collectors.toList());

        return modelMapper.map(filtered, targetListType);
    }

    public LocationDto findById(long id) {
        Location location = locations.stream()
                .filter(loc -> loc.getId() == id)
                .findFirst()
                .get();
        return modelMapper.map(location, LocationDto.class);
    }


    public LocationDto createLocation(CreateLocationCommand command) {
            Location location = new Location(id.incrementAndGet(), command.getName(), command.getLat(), command.getLon());
            locations.add(location);
            return modelMapper.map(location, LocationDto.class);
    }

    public void deleteLocation(long id) {
        Location location = locations.stream()
                .filter(loc -> loc.getId() == id)
                .findFirst()
                .get();
        locations.remove(location);
    }

    public LocationDto updateLocation(long id, UpdateLocationCommand command) {
        int index = IntStream.range(0, locations.size())
                .filter(i -> locations.get(i).getId() == id)
                .findFirst()
                .getAsInt();

        if (!command.getName().isEmpty()) {
            locations.get(index).setName(command.getName());
        }
        if (command.getLat() != null) {
            locations.get(index).setLat(command.getLat());
        }
        if (command.getLon() != null) {
            locations.get(index).setLon(command.getLon());
        }

        return modelMapper.map(locations.get(index), LocationDto.class);
    }

    public void deleteAllLocations() {
        id = new AtomicLong();
        locations.clear();
    }
}
