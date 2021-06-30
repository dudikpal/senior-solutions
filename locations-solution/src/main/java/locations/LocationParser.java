package locations;

public class LocationParser {

    public Location parse(String text) {

        String[] datas = text.split(",");

        return new Location(datas[0],
                Double.parseDouble(datas[1]),
                Double.parseDouble(datas[2]));
    }
}
