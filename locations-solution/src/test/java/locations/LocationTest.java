package locations;

import locations.Location;
import locations.LocationParser;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LocationTest {

    private LocationParser locationParser;
    Location ilk;
    Location tarpa;
    Location tyukod;

    @BeforeEach
    void init() {

        locationParser = new LocationParser();
        ilk = locationParser.parse("Ilk,48.12032678678954, 22.233085084006255");
        tarpa = locationParser.parse("Tarpa,48.10519792493993, 22.532119205058418");
        tyukod = locationParser.parse("Tyukod,47.850941955823146, 22.56022129415609");
    }

    @Test
    void test_Parse() {

        String input = "Aba,23.123456,3.867432";

        String expectedName = "Aba";
        double expectedLat = 23.123456;
        double expectedLong = 3.867432;
        Location location = new LocationParser().parse(input);

        assertEquals(expectedName, location.getName());
        assertEquals(expectedLat, location.getLat());
        assertEquals(expectedLong, location.getLon());
    }

    @Test
    void test_Not_Same_Instance() {

        String input = "Aba,23.123456,3.867432";
        assertNotSame(new LocationParser().parse(input), new LocationParser().parse(input));
    }

    @Test
    void test_On_Prime_Meridian_Or_Equator() {

        assertAll(
                () -> assertTrue(locationParser.parse("Baja,0.0,23.456987").isOnEquator()),
                () -> assertTrue(locationParser.parse("Baja,23.456987,0.0").isOnPrimeMeridian())
        );
    }

    @Test
    void test_Distances() {



        DecimalFormat format = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));

        assertAll(
                () -> assertEquals(22264.27, Double.valueOf(format.format(ilk.distanceFrom(tarpa)))),
                () -> assertEquals(38600.85, Double.valueOf(format.format(ilk.distanceFrom(tyukod)))),
                () -> assertEquals(28349.25, Double.valueOf(format.format(tyukod.distanceFrom(tarpa))))
        );
    }

    @Test
    void test_All_Attribs() {
        Location location = locationParser.parse("Terem,45.454545,44.444444");
        assertAll(
                () -> assertEquals("Terem", location.getName()),
                () -> assertEquals(45.454545, location.getLat()),
                () -> assertEquals(44.444444, location.getLon())
        );
    }

    @Test
    void test_Coordinates_Validation() {
        String wrongLat1 = "Errorland,-91,100";
        assertThrows(IllegalArgumentException.class, () -> locationParser.parse(wrongLat1));
        String wrongLat2 = "Errorland,91,100";
        assertThrows(IllegalArgumentException.class, () -> locationParser.parse(wrongLat2));
        String wrongLon1 = "Errorland,-51,-190";
        assertThrows(IllegalArgumentException.class, () -> locationParser.parse(wrongLon1));
        String wrongLon2 = "Errorland,-51,199";
        assertThrows(IllegalArgumentException.class, () -> locationParser.parse(wrongLon2));
    }

    String[][] arr = {
            {"Ilk,48.12032678678954, 22.233085084006255", "false"},
            {"Tarpa,48.10519792493993, 22.532119205058418", "false"},
            {"Tyukod,47.850941955823146, 22.56022129415609", "false"},
            {"Eq1,0,34.53321", "true"},
            {"Eq2,0,32.224", "true"}
    };

    @RepeatedTest(value = 5, name = "On Equator {currentRepetition} / {totalRepetitions}")
    void test_On_Equator(RepetitionInfo repetitionInfo) {
        Location location = locationParser.parse(arr[repetitionInfo.getCurrentRepetition() - 1][0]);
        assertEquals(Boolean.parseBoolean(arr[repetitionInfo.getCurrentRepetition() - 1][1]), location.isOnEquator());
    }

    @ParameterizedTest(name = "locations.Location:{0} is on prime meridian is {1}")
    @MethodSource("createOnPrimeMeridians")
    void test_On_Prime_Meridians_By_Parameterized_Test_With_MethodSource(String locationString, boolean isOnPrimeMeridian) {
        Location location = locationParser.parse(locationString);
        assertEquals(isOnPrimeMeridian, location.isOnPrimeMeridian());
    }

    static Stream<Arguments> createOnPrimeMeridians() {
        return Stream.of(
                Arguments.arguments("Baja,0,1", false),
                Arguments.arguments("Baja,1,0", true)
        );
    }

    @ParameterizedTest(name = "locations.Location:{0} is on prime meridian is {1}")
    @CsvFileSource(resources = "/data.csv")
    void test_On_Prime_Meridians_By_Parameterized_Test_With_CsvFileSource(double lat1, double lon1, double lat2, double lon2, double distance) {
        Location location1 = new Location("Baj", lat1, lon1);
        Location location2 = new Location("Fej", lat2, lon2);
        assertEquals(distance, location1.distanceFrom(location2), 0.5);
    }

    @TestFactory
    Stream<DynamicTest> isOnEquator() {
        return Stream.of("Job,0,1", "Bal,0,44")
                .map(location ->
                        DynamicTest.dynamicTest(
                                "Is on equator? " + location,
                                () -> assertTrue(locationParser.parse(location).isOnEquator())
                        ));
    }



}