package moviesquerypractice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    // Kérdezzük le azokat filmeket, amelyekben a paraméterül átadott nevű színész szerepel!
    @Query("select m from Movie m join fetch m.actors a where a.name = :name")
    List<Movie> listAllMoviesWithActor(@Param("name") String name);


    // Kérdezzük le azokat a filmeket amik egy paraméterül átadott stúdióban készültek egy paraméterül átadott évben!
    @Query("select m from Movie m join fetch m.studio s where m.year = :year and s.name = :name")
    List<Movie> listMoviesInStudioInYear(@Param("name") String name, @Param("year") int year);
}
