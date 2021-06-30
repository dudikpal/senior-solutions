package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@LocationOperationsFeatureTest
class LocationOperatorsTest {

    private List<Location> locations;
    LocationParser locationParser;

    @BeforeEach
    void setUp() {
        locationParser = new LocationParser();
        Location ilk = locationParser.parse("Ilk,48.12032678678954, 22.233085084006255");
        Location tarpa = locationParser.parse("Tarpa,48.10519792493993, 22.532119205058418");
        Location tyukod = locationParser.parse("Tyukod,47.850941955823146, 22.56022129415609");
        Location deli = locationParser.parse("Deli,-47.850941955823146, 22.56022129415609");
        locations = new ArrayList<>();
        locations.add(ilk);
        locations.add(tarpa);
        locations.add(tyukod);
        locations.add(deli);
    }

    @Test
    void testOnlyNorth() {
        assertFalse(locations.stream()
                        .filter(location -> location.getLat() > 0)
                        .map(Location::getName)
                        .collect(Collectors.toList())
                        .contains("Deli"));
    }

}