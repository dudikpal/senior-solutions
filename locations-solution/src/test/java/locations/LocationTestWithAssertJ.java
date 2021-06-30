package locations;


import locations.Location;
import locations.LocationService;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LocationTestWithAssertJ {

    private LocationParser locationParser = new LocationParser();
    private LocationService locationService = new LocationService();

    @Test
    void test_With_AssertJ() throws IOException {
        List<Location> locations = locationService.locationsFromFile(Path.of("src/main/resources/hamcrest.txt"));
        assertThat(locations)
            .hasSize(5);
        assertThat(locations.stream()
                        .map(Location::getName)
                        .collect(Collectors.toList()))
                .contains("Tarpa", "Ilk")
                .doesNotContain("Aba");

        assertThat(locations)
                .extracting("name")
                .contains("Ilk");

        assertThat(locations)
                .extracting(Location::getName)
                .contains("Ilk");
    }

    /*
    Definiálj egy olyan  Condition<Location> feltételt, ami azt vizsgálja,
    hogy a kedvenc hely legalább egyik koordinátája 0.
     */

    @Test
    void test_With_Custom_Condition() {

        Location location = locationParser.parse("Baja,0.0,10.0");
        Condition<Location> zeroCoord = new Condition<>(
                loc -> loc.getLon() == 0.0 || loc.getLat() == 0.0, "Lon is 0"
        );

        assertThat(location).has(zeroCoord);
    }
}
