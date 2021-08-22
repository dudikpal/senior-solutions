package microservices.training.movies;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MovieService {

    private List<Movie> movies = new ArrayList<>();
    private AtomicLong idGenerator = new AtomicLong();
    private ModelMapper modelMapper;

    public MovieService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<MovieDto> getMovies(Optional<String> title) {
        Type targetListType = new TypeToken<List<MovieDto>>(){}.getType();
        List<Movie> filtered = movies.stream()
                .filter(movie -> title.isEmpty() || movie.getTitle().equalsIgnoreCase(title.get()))
                .collect(Collectors.toList());
        return modelMapper.map(filtered, targetListType);
    }

    public MovieDto createMovie(CreateMovieCommand command) {
        Movie movie = new Movie(idGenerator.incrementAndGet(), command.getTitle(), command.getLength());
        movies.add(movie);
        return modelMapper.map(movie, MovieDto.class);
    }

    public MovieDto findMovieById(long id) {
        return movies.stream()
                .filter(movie -> movie.getId() == id)
                .findFirst()
                .map(movie -> modelMapper.map(movie, MovieDto.class))
                .get();
    }


    public double getRatingmovie(long id) {
        int index = IntStream.range(0, movies.size())
                .filter(i -> movies.get(i).getId() == id)
                .findFirst()
                .getAsInt();
        return movies.get(index).getRatingsAvg();
    }


    public MovieDto ratingmovie(long id, RatingMovieCommand command) {
        int index = IntStream.range(0, movies.size())
                .filter(i -> movies.get(i).getId() == id)
                .findFirst()
                .getAsInt();
        movies.get(index).addRating(command.getRating());
        return modelMapper.map(movies.get(index), MovieDto.class);
    }

    public void deleteMovie(long id) {
        Movie movie = movies.stream()
                .filter(mov -> mov.getId() == id)
                .findFirst()
                .get();
        movies.remove(movie);
    }

    public void deleteAllMovies() {
        idGenerator = new AtomicLong();
        movies.clear();
    }

}
