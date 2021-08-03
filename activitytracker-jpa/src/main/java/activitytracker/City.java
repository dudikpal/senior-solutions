package activitytracker;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int population;

    @ManyToOne
    @JoinColumn(name = "area_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Area area;

    public City(String name, int population) {
        this.name = name;
        this.population = population;
    }
}
