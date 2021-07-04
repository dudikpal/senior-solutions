package microservices.training.movies;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieControllerTestIT {

    @Autowired
    TestRestTemplate template;

    @Autowired
    MovieService movieService;

    @Test
    void getMovies() {
        movieService.deleteAllMovies();

        MovieDto dto = template.postForObject("/api/movies",
                new CreateMovieCommand("Tatanih", 120),
                MovieDto.class);

        assertEquals("Tatanih",dto.getTitle());

        template.postForObject("/api/movies",
                new CreateMovieCommand("Titinih", 110),
                MovieDto.class);

        List<MovieDto> movies = template.exchange("/api/movies",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MovieDto>>() {})
                .getBody();

        assertThat(movies)
                .extracting("title")
                .containsExactly("Tatanih", "Titinih");
    }
}