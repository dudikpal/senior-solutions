package moviesquerypractice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudioRepository extends JpaRepository<Studio, Long> {

    // Kérdezzük le azokat a stúdiókat melyek legalább 2 filmmel büszkélkedhetnek!
    @Query("select s from Studio s join fetch s.movies where s.movies.size > 1")
    List<Studio> studiosWithMoreMovies();

    // Kérdezzük le azokat a stúdiókat, ahol egy paraméterül átadott színész szerepelt legalább egy filmben!
    @Query("select s from Studio s join fetch s.movies m join fetch m.actors a where a.name = :name")
    List<Studio> findStudioWithActor(@Param("name") String name);

    // Adjuk vissza azt a stúdiót, ahol egy színész legalább két filmben szerepel!
    //    SELECT DISTINCT studios.id from actor_movies
    //LEFT JOIN movies ON movies.id = actor_movies.movies_id LEFT JOIN studios ON studios.id = movies.studio_id
    // LEFT JOIN actor ON actor.id = actor_movies.actors_id
    // GROUP BY actor.name, movies.studio_id
    // HAVING COUNT(studios.name)>1
    @Query("select distinct s from Movie m left join  m.studio s left join  m.actors a  " +
            "group by a.name ,m.studio.id having count(s.name)>1")
    List<Studio> findStudioWhereActorAtLeastTwoMovies(String name);
}
