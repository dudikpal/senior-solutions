package locations;

import java.util.Optional;

public class DistanceService {

    private LocationRepository locationRepository;

    public DistanceService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Optional<Double> calculateDistance(String name1, String name2) {
        Optional<Location> loc1 = locationRepository.findByName(name1);
        Optional<Location> loc2 = locationRepository.findByName(name2);

        if (loc1.isEmpty() || loc2.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(loc1.get().distanceFrom(loc2.get()));
    }
}
