package microservices.training.movies;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/movies")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieDto> getMovies(@RequestParam Optional<String> title) {
        return movieService.getMovies(title);
    }

    @GetMapping("/{id}")
    public MovieDto findMovieById(@PathVariable("id") long id) {
        return movieService.findMovieById(id);
    }

    @PostMapping
    public MovieDto createMovie(@RequestBody CreateMovieCommand command) {
        return movieService.createMovie(command);
    }


    @GetMapping("/{id}/rating")
    public double getRatingMovie(@PathVariable("id") long id) {
        return movieService.getRatingmovie(id);
    }


    @PutMapping("/{id}/rating")
    public MovieDto ratingMovie(@PathVariable("id") long id, @RequestBody RatingMovieCommand command) {
        return movieService.ratingmovie(id, command);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable("id") long id) {
        movieService.deleteMovie(id);
    }
}
