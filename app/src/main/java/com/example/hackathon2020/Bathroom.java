package com.example.hackathon2020;

import com.google.android.gms.maps.model.LatLng;


public class Bathroom {
    private String name;
    private String picture;
    private String mapID;
    private LatLng location;

    private double totalRatings;
    private int numRatings;

    private int numStalls;
    private int numSinks;
    private int numUrinals;

    private boolean isMale;
    private boolean isFemale;
    private boolean isOther;

    private boolean isWheelchair;
    private boolean isFamily;

    public Bathroom(String name, String pic, String mapID, LatLng loc, int numStalls, int numSinks, int numUrinals,
                    boolean isMale, boolean isFemale, boolean isOther, boolean isWheelchair, boolean isFamily) {
        this.name = name;
        this.picture = pic;
        this.mapID = mapID;
        this.location = loc;
        this.totalRatings = 0.0;
        this.numRatings = 0;
        this.numStalls = numStalls;
        this.numSinks = numSinks;
        this.numUrinals = numUrinals;
        this.isMale = isMale;
        this.isFemale = isFemale;
        this.isOther = isOther;
        this.isWheelchair = isWheelchair;
        this.isFamily = isFamily;
    }

    public String getMapID() {
        return this.mapID;
    }

    public String getName() {
        return this.name;
    }

    public String getPicture() {
        return this.picture;
    }

    public LatLng getLocation() {
        return this.location;
    }

    public double getRating() {
        double temp = this.totalRatings/this.numRatings;
        return Math.round(temp * 10) / 10.0;
    }

    public void addRating(double newRating) {
        this.totalRatings += newRating;
        this.numRatings++;
    }

    public String getProperties() {
        String out = this.getRating() + "\n";


        if (this.isMale) {
            out += "Male\n" + this.numUrinals + "\n" ;
        }
        else if (this.isFemale) {
            out += "Female\n";
        }
        else if (this.isOther) {
            out += "Gender Neutral\n";
        }

        out += + this.numSinks + "\n" + this.numStalls + "\n";

        if (isWheelchair && isFamily) {
            out += "Wheelchair\n" + "Family\n";
        }

        if (isWheelchair) {
            out += "Wheelchair\n";
        }

        if (isFamily) {
            out += "Family\n";
        }

        return out;
    }
}