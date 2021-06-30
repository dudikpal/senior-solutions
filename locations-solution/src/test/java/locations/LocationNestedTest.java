package locations;

import locations.Location;
import locations.LocationParser;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class LocationNestedTest {

    LocationParser locationParser;

    @BeforeEach
    void setUp() {
        locationParser = new LocationParser();
    }

    @Nested
    class Nested1 {

        Location favourite;

        @BeforeEach
        void setUp() {
            favourite = locationParser.parse("Fav1,0,0");
        }

        @Test
        void test_On_Equator() {
            assertTrue(favourite.isOnEquator());
        }

        @Test
        void on_Prime_Meridian() {
            assertTrue(favourite.isOnPrimeMeridian());
        }
    }

    @Nested
    class Nested2 {

        Location favourite;

        @BeforeEach
        void setUp() {
            favourite = locationParser.parse("Fav2,47.497912,19.040235");
        }

        @Test
        void test_On_Equator() {
            assertFalse(favourite.isOnEquator());
        }

        @Test
        void on_Prime_Meridian() {
            assertFalse(favourite.isOnPrimeMeridian());
        }
    }

}
