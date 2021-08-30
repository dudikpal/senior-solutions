package moviesquerypractice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ActorRepositoryIT {

    @Autowired
    ActorRepository actorRepository;

    @Autowired
    MovieRepository movieRepository;

    Actor actor1;
    Actor actor2;
    Actor actor3;
    Movie movie1;
    Movie movie2;

    @BeforeEach
    void setUp() {
        actor1 = new Actor("actor1", LocalDate.of(1999, 4, 3));
        actor2 = new Actor("actor2", LocalDate.of(2000, 4, 3));
        actor3 = new Actor("actor3", LocalDate.of(2001, 4, 3));
        movie1 = new Movie("movie1", 2010);
        movie2 = new Movie("movie2", 2011);
        actorRepository.save(actor1);
        actorRepository.save(actor2);
        actorRepository.save(actor3);
        movie1.addActor(actor1);
        movie1.addActor(actor2);
        movie2.addActor(actor2);
        movie2.addActor(actor3);
        movieRepository.save(movie1);
        movieRepository.save(movie2);
    }


    @Test
    void testActorsByDateOfBirthAfter() {
        assertThat(actorRepository.findActorsByDateOfBirthAfter(1999))
                .extracting(Actor::getName)
                .containsExactly("actor2", "actor3");
    }

    @Test
    void testfindActorByMovie() {
        assertThat(actorRepository.findActorByMovie("movie2"))
                .extracting(Actor::getName)
                .containsExactly("actor2", "actor3");
    }



}