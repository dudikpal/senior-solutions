package jun09;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MovieService {

    private List<Movie> movies = new ArrayList<>();

    public void save(Movie movie) {
        movies.add(movie);
    }

    public Movie newestMovie() {
        return movies.stream()
                .sorted(Comparator.comparing(Movie::getReleaseDate).reversed())
                .findFirst()
                .get();
    }

    public List<Movie> findMovieByNamePart(String titlePart) {
        return movies.stream()
                .filter(movie -> movie.getName().contains(titlePart))
                .collect(Collectors.toList());
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
