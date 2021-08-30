package moviesquerypractice;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private int year;

    public Movie(String title, int year) {
        this.title = title;
        this.year = year;
    }


    @ManyToMany(mappedBy = "movies")
    @EqualsAndHashCode.Exclude
    private Set<Actor> actors = new HashSet<>();


    @ManyToOne
    @ToString.Exclude
    private Studio studio;


    public void addActor(Actor actor) {
        actors.add(actor);
        actor.getMovies().add(this);
    }
}
