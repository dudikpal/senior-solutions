package moviesquerypractice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MovieRepositoryIT {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ActorRepository actorRepository;

    @Autowired
    StudioRepository studioRepository;

    Actor actor1;
    Actor actor2;
    Actor actor3;
    Movie movie1;
    Movie movie2;
    Movie movie3;
    Studio studio1;
    Studio studio2;


    @BeforeEach
    void setUp() {
        actor1 = new Actor("actor1", LocalDate.of(1999, 4, 3));
        actor2 = new Actor("actor2", LocalDate.of(2000, 4, 3));
        actor3 = new Actor("actor3", LocalDate.of(2001, 4, 3));
        movie1 = new Movie("movie1", 2010);
        movie2 = new Movie("movie2", 2011);
        movie3 = new Movie("movie3", 2012);
        studio1 = new Studio("studio1");
        studio2 = new Studio("studio2");
        movie1.addActor(actor1);
        movie1.addActor(actor2);
        movie2.addActor(actor2);
        movie2.addActor(actor3);
        movie3.addActor(actor1);
        studio1.addMovie(movie1);
        studio1.addMovie(movie2);
        studio2.addMovie(movie3);
        studioRepository.save(studio1);
        studioRepository.save(studio2);
        movieRepository.save(movie1);
        movieRepository.save(movie2);
        movieRepository.save(movie3);
        actorRepository.save(actor1);
        actorRepository.save(actor2);
        actorRepository.save(actor3);
    }

    @Test
    void testMovieWithActor() {

        List<Movie> movies = movieRepository.listAllMoviesWithActor("actor1");

        assertThat(movies)
                .extracting(Movie::getTitle)
                .contains("movie3");
    }

    @Test
    void testfindActorsWithMoreMovies() {
        assertThat(actorRepository.findActorsWithMoreMovies())
                .extracting(Actor::getName)
                .containsExactly("actor1", "actor2");
    }

    @Test
    void testListMoviesInStudioInYear() {
        assertThat(movieRepository.listMoviesInStudioInYear("studio1", 2011))
            .extracting(Movie::getTitle)
            .containsExactly("movie2");
    }

    @Test
    void findStudioWithActor() {
        assertThat(studioRepository.findStudioWithActor("actor1"))
                .extracting(Studio::getName)
                .containsExactly("studio1", "studio2");
    }

    @Test
    void testFindStudioWhereActorAtLeastTwoMovies() {
        assertThat(studioRepository.findStudioWhereActorAtLeastTwoMovies("actor2"))
                .extracting(Studio::getName)
                .containsExactly("studio1");
    }

}