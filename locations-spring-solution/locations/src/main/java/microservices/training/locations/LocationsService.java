package microservices.training.locations;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class LocationsService {

    private ModelMapper modelMapper;

    private List<Location> locations = new ArrayList<>(List.of(
            new Location(1L, "name1", 2.22, 3.33),
            new Location(2L, "name2", 5.55, 4.44)
    ));

    public LocationsService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<LocationDto> getLocations() {
        Type targetListType = new TypeToken<List<LocationDto>>(){}.getType();
        return modelMapper.map(locations, targetListType);
    }
}
