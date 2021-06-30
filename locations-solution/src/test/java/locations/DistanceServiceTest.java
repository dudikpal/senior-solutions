package locations;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class DistanceServiceTest {

    @Mock
    LocationRepository locationRepository;

    @InjectMocks
    DistanceService distanceService;

    @Test
    void test_Locations_Validation_Check() {

        when(locationRepository.findByName("Baja")).thenReturn(
                Optional.of(new Location("Baja", 11.11, 22.22))
        );
        when(locationRepository.findByName("Ilk")).thenReturn(
                Optional.of(new Location("Ilk", 11.12, 22.23))
        );

        assertNotEquals(Optional.empty(), distanceService.calculateDistance("Baja", "Ilk"));
        assertEquals(Optional.empty(), distanceService.calculateDistance("Baja", "Kaeet"));
    }

}