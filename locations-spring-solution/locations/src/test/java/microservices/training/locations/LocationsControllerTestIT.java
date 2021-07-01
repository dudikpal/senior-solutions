package microservices.training.locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LocationsControllerTestIT {


    @Autowired
    LocationsController locationsController;

    @Test
    void getLocations() {

        String locations = locationsController.getLocations();

        assertThat(locations)
                .contains("name1", "name2")
                .contains("3.33", "2.22");
    }
}