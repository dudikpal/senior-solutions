package locations;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class LocationService {

    private LocationParser locationParser = new LocationParser();
    private String SEPARATOR = ",";

    public List<Location> locationsFromFile(Path file) throws IOException {
        List<String> locationsString = Files.readAllLines(file);
        return locationsString.stream()
                .map(x -> locationParser.parse(x))
                .collect(Collectors.toList());
    }

    public void writeLocations(Path file, List<Location> locations) {

        try (BufferedWriter bw = Files.newBufferedWriter(file)) {

            for (Location location : locations) {
                bw.write(location.getName());
                bw.write(SEPARATOR);
                bw.write(String.valueOf(location.getLat()));
                bw.write(SEPARATOR);
                bw.write(String.valueOf(location.getLon()));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
