package jun09;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MovieServiceTest {

    MovieService movieService;

    @BeforeEach
    void setUp() {
        movieService = new MovieService();
        movieService.save(new Movie("elsofilm", 134, LocalDate.of(2000, 01, 01)));
        movieService.save(new Movie("masodikfilm", 114, LocalDate.of(2000, 01, 05)));
    }

    @Test
    void test_Save_Movie() {
        movieService.save(new Movie("harmadikfilm", 174, LocalDate.of(1999, 01, 01)));
        System.out.println("savemovie: " + movieService.getMovies().size());
        assertEquals(3, movieService.getMovies().size());
    }

    @Test
    void test_Find_By_Part() {
        System.out.println("findByPart: " + movieService.getMovies().size());
        movieService.findMovieByNamePart("film");
    }

    @Test
    void test_Newest_Movie() {
        System.out.println("NewestMovie: " + movieService.getMovies().size());
        assertEquals(114, movieService.newestMovie().getLength());
    }


}