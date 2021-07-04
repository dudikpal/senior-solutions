package microservices.training.movies;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    void setRatingsAvg() {

        Movie movie = new Movie(1L, "Tatana", 100);
        movie.addRating(5);
        movie.addRating(1);

        assertEquals(2, movie.getRatings().size());
        assertEquals(3.0, movie.getRatingsAvg());
    }
}