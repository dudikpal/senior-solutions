package locations;

public class Location {

    private String name;
    private double lat;
    private double lon;

    public Location(String name, double lat, double lon) {
        coordinateValidator(lat, lon);
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    private void coordinateValidator(double lat, double lon) {
        if (lat < -90 || lat > 90 || lon < -180 || lon > 180) {
            throw new IllegalArgumentException("Not valid coordinates");
        }
    }

    public boolean isOnEquator() {
        return lat == 0;
    }

    public boolean isOnPrimeMeridian() {
        return lon == 0;
    }

    public double distanceFrom(Location toLocation) {

        final int R = 6371; // Radius of the earth
        double lat1 = this.lat;
        double lon1 = this.lon;
        double lat2 = toLocation.lat;
        double lon2 = toLocation.lon;
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000;

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
