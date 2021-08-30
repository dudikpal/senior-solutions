package moviesquerypractice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {

    // Kérdezzük le azokat a színészeket, akik egy bizonyos év után születtek!
    @Query("select a from Actor a where YEAR(a.dateOfBirth) > :date")
    List<Actor> findActorsByDateOfBirthAfter(@Param("date") int date);


    // Kérdezzük le azokat a színészeket akik több filmben is szerepelnek!
    @Query("select distinct a from Actor a join fetch a.movies m where a.movies.size > 1")
    List<Actor> findActorsWithMoreMovies();


    // Kérdezzük le azokat a színészeket, akik egy paraméterül átadott filmben szerepelnek!
    @Query("select a from Actor a join fetch a.movies m where m.title = :title")
    List<Actor> findActorByMovie(@Param("title") String title);
}
