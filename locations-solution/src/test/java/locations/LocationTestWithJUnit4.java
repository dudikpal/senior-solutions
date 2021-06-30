package locations;

import locations.Location;
import locations.LocationParser;
import org.junit.Test;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

import static org.junit.Assert.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class LocationTestWithJUnit4 {

    @Test
    public void test_Parse_With_JUnit4() {

        String input = "Aba,23.123456,3.867432";

        String expectedName = "Aba";
        double expectedLat = 23.123456;
        double expectedLong = 3.867432;
        Location location = new LocationParser().parse(input);

        assertEquals(expectedName, location.getName());
        assertEquals(expectedLat, location.getLat(), 0.05);
        assertEquals(expectedLong, location.getLon(), 0.05);
    }
}
