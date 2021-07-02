package microservices.training.locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationsControllerTest {

    @Mock
    LocationsService locationsService;

    @InjectMocks
    LocationsController locationsController;

    @Test
    void getLocations() {

        when(locationsService.getLocations(any(), any(), any(), any(), any()))
                .thenReturn(List.of(
                        new LocationDto(1L, "name1", 1.11, 2.22)
                ));

        assertThat(locationsController.getLocations(any(), any(), any(), any(), any()))
                .extracting("name")
                .contains("name1")
                .doesNotContain("name3");
    }
}