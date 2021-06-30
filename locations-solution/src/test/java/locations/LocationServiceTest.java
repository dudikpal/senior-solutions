package locations;

import locations.Location;
import locations.LocationParser;
import locations.LocationService;
import locations.LocationWithNameMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LocationServiceTest {

    LocationService locationService;
    LocationParser locationParser;

    @BeforeEach
    void SetUp() {
        locationService = new LocationService();
        locationParser = new LocationParser();
    }

    @TempDir
    Path tempDir;

    @Test
    void test_Write_Locations_To_File() throws IOException {
        Path file = tempDir.resolve("temp.txt");
        locationService.writeLocations(file, List.of(
                locationParser.parse("Baja,33,22"),
                locationParser.parse("Beje,11,22"),
                locationParser.parse("Buju,66,44")
        ));
        List<String> locations = Files.readAllLines(file);
        assertAll(
                () -> assertEquals("Beje,11.0,22.0", locations.get(1)),
                () -> assertEquals("Buju,66.0,44.0", locations.get(2))
        );
    }

    @Test
    void test_With_Hamcrest() throws IOException{

        List<Location> locations = locationService.locationsFromFile(Path.of("src/main/resources/hamcrest.txt"));
        assertThat(locations,
                hasItem(hasProperty("lat", closeTo(47.850941955823146, 0.000001))));
    }

    @Test
    void test_With_Custom_Matcher() {
        Location location = locationParser.parse("Baja,23.22,22.88");
        assertThat(location, LocationWithNameMatcher.locationWithName(startsWith("Baj")));
    }


}