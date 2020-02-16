package com.example.hackathon2020;

import java.util.Comparator;

public class CompareBathrooms implements Comparator<Bathroom> {

    private Geolocation userLocation;

    public CompareBathrooms(Geolocation currentLoc) {
        this.userLocation = currentLoc;
    }

    public Geolocation getUserLocation() {
        return this.userLocation;
    }

    @Override
    public int compare(Bathroom bath1, Bathroom bath2) {

        Geolocation loc1 = bath1.getGeolocation();
        Geolocation loc2 = bath2.getGeolocation();

        double distanceToPlace1 = calcDist(getUserLocation(), loc1);
        double distanceToPlace2 = calcDist(getUserLocation(), loc2);
        return (int) (distanceToPlace1 - distanceToPlace2);
    }

    /**
     * MODIFIED FROM: https://stackoverflow.com/questions/3694380/calculating-distance-between-two-points-using-latitude-longitude
     *
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     *
     * @returns Distance in Meters
     */
    public static double calcDist(Geolocation loc1, Geolocation loc2) {

        double lat1 = loc1.getLatitude();
        double lat2 = loc2.getLatitude();

        double lon1 = loc1.getLongitude();
        double lon2 = loc2.getLongitude();

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        return distance;
    }
}
