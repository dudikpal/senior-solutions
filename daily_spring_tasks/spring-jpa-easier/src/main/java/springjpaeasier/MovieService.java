package springjpaeasier;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieService {

    private MovieRepository movieRepository;

    private ModelMapper modelMapper;


    public List<MovieDTO> getMovies() {
        return movieRepository.findAll().stream()
                .map(m -> modelMapper.map(m, MovieDTO.class))
                .toList();
    }

    public MovieDTO createMovie(CreateMovieCommand command) {
        Movie movie = new Movie(command.getTitle());
        movieRepository.save(movie);
        return modelMapper.map(movie, MovieDTO.class);
    }

    @Transactional
    public MovieDTO addNewRating(long id, CreateRatingCommand command) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot find movie"));
        movie.addRating(command.getRating());
        return modelMapper.map(movie, MovieDTO.class);
    }
}
