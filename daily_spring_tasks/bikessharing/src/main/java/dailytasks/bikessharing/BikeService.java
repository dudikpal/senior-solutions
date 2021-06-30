package dailytasks.bikessharing;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BikeService {

    private List<Share> shares;

    public List<Share> allShare() {
        listChecker();
        return shares;
    }

    private void fillSharesFromFile() {
        InputStream is = this.getClass().getResourceAsStream("/bikes.csv");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            shares = new ArrayList();
            while ((line = br.readLine()) != null) {
                String[] words = line.split(";");
                String bikeId = words[0];
                String userId = words[1];
                LocalDateTime dateTime = dateTimeParser(words[2]);
                double km = Double.parseDouble(words[3]);
                shares.add(new Share(bikeId, userId, dateTime, km));

            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file, ", ioe);
        }
    }

    private LocalDateTime dateTimeParser(String dateTime) {
        String[] dateParts = dateTime.split("[-\\s:]");
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public List<Share> getShares() {
        return shares;
    }

    public List<String> userIds() {
        listChecker();
        return shares.stream()
                .map(share -> share.getUserId())
                .collect(Collectors.toList());
    }

    private void listChecker() {
        if (shares == null) {
            fillSharesFromFile();
        }
    }
}
