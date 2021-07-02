package microservices.training.locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LocationsControllerTestIT {


    @Autowired
    LocationsController locationsController;

    @Test
    void getLocations() {

        List<LocationDto> locations = locationsController.getLocations(Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty());

        assertThat(locations)
                .flatExtracting(LocationDto::getName, LocationDto::getLat)
                .contains("name3", 4.44);
    }
}