package microservices.training.locations;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LocationsServiceTest {

    LocationsService locationsService;

    @Test
    void getLocations() {
        locationsService = new LocationsService();

        assertThat(locationsService.getLocations())
                .hasSize(2)
                .extracting("name")
                .contains("name1")
                .doesNotContain("name3");
    }
}