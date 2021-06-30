package jun15;

import java.util.*;
import java.util.stream.Collectors;

public class GameService {

    private GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game biggestGoalDifference() {
        return gameRepository.getGames().stream()
                .max(Comparator.comparing(game -> Math.abs(game.getFirstCountryScore()) - game.getSecondCountryScore()))
                .get();
    }

    public int sumGoalOfCountry(String country) {
        List<Game> firstCountryGames = gameRepository.getGames().stream()
                .filter(game -> game.getFirstCountry().equals(country))
                .collect(Collectors.toList());
        List<Game> secondCountryGames = gameRepository.getGames().stream()
                .filter(game -> game.getSecondCountry().equals(country))
                .collect(Collectors.toList());
        int sum = firstCountryGames.stream()
                .map(game -> game.getFirstCountryScore())
                .reduce(0, (i, j) -> i + j);
        sum += secondCountryGames.stream()
                .map(game -> game.getSecondCountryScore())
                .reduce(0, (i, j) -> i + j);

        return sum;
    }

    public String mostGoalsCountry() {
        Map<String, Integer> results = new HashMap<>();
        gameRepository.getGames().stream()
                .forEach(country -> {
                    results.merge(country.getFirstCountry(), country.getFirstCountryScore(), Integer::sum);
                    results.merge(country.getSecondCountry(), country.getSecondCountryScore(), Integer::sum);

                });

        return results.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get()
                .getKey();
    }
}
