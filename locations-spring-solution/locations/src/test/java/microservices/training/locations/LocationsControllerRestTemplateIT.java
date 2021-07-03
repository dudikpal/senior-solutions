package microservices.training.locations;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LocationsControllerRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @Autowired
    LocationsService locationsService;

    @Test
    @RepeatedTest(2)
    void testListLocations() {
        locationsService.deleteAllLocations();

        template.postForObject("/locations", new CreateLocationCommand("name1", 1.11, 2.22), LocationDto.class);
        template.postForObject("/locations", new CreateLocationCommand("name2", 1.11, 2.22), LocationDto.class);

        List<LocationDto> locations = template
                .exchange("/locations",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<LocationDto>>() {})
                .getBody();
        assertThat(locations)
                .extracting("name")
                .containsExactly("name1", "name2");

    }
}
