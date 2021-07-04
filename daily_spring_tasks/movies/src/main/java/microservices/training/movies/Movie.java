package microservices.training.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private long id;
    private String title;
    private int length;
    private List<Integer> ratings = new ArrayList<>();
    private double ratingsAvg;

    public Movie(long id, String title, int length) {
        this.id = id;
        this.title = title;
        this.length = length;
    }

    public void setRatingsAvg() {
        ratingsAvg = ratings.stream()
                .mapToDouble(Integer::doubleValue)
                .average()
                .getAsDouble();
    }

    public void addRating(int rating) {
        ratings.add(rating);
        setRatingsAvg();
    }
}
