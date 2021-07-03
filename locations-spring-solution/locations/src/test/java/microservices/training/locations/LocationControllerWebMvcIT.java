package microservices.training.locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(controllers = LocationsController.class)
public class LocationControllerWebMvcIT {

    @MockBean
    LocationsService locationsService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testListLocations() throws Exception{
        when(locationsService.getLocations(any(), any(), any(), any(), any()))
                .thenReturn(List.of(
                        new LocationDto(1L, "name1", 1.11, 2.22),
                        new LocationDto(2L, "name2", 2.22, 3.33)
                ));
        mockMvc.perform(get("/locations"))
                .andDo(print());
    }
}
