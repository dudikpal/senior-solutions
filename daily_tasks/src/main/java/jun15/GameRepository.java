package jun15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GameRepository {

    private List<Game> games = new ArrayList<>();

    public void addGame(Game game) {
        games.add(game);
    }

    public void addGameFromFile(String file) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Path.of(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String line : lines) {
            String[] words = line.split(";");
            addGame(new Game(words[0], words[1], Integer.parseInt(words[2]), Integer.parseInt(words[3])));
        }
    }

    public List<Game> getGames() {
        return games;
    }
}
