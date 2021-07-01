package microservices.training.locations;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LocationsServiceTest {

    ModelMapper modelMapper = new ModelMapper();
    LocationsService locationsService;

    @Test
    void getLocations() {
        locationsService = new LocationsService(modelMapper);

        assertThat(locationsService.getLocations())
                .hasSize(2)
                .extracting("name")
                .contains("name1")
                .doesNotContain("name3");
    }
}