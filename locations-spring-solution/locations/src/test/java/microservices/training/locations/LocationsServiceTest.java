package microservices.training.locations;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class LocationsServiceTest {

    ModelMapper modelMapper = new ModelMapper();
    LocationsService locationsService;

    @Test
    void getLocations() {
        locationsService = new LocationsService(modelMapper);

        assertThat(locationsService.getLocations(Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()))
                .hasSize(3)
                .extracting("name")
                .contains("name1")
                .doesNotContain("name4");
    }
}