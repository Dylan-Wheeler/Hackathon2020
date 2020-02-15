package com.example.hackathon2020;

import com.google.android.gms.maps.*;


public class Bathroom {
    private String name;
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

    public Bathroom(String name, String mapID, LatLng loc, int numStalls, int numSinks, int numUrinals,
                    boolean isMale, boolean isFemale, boolean isOther, boolean isWheelchair, boolean isFamily) {
        this.name = name;
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

    public double getRating() {
        double temp = this.totalRatings/this.numRatings;
        return Math.round(temp * 10) / 10.0;
    }

    public void addRating(double newRating) {
        this.totalRatings += newRating;
        this.numRatings++;
    }

    @Override
    public String toString() {
        String out = this.name + "\n" + this.mapID + "\n" +
                     this.location.toString() + "\n" + this.getRating() + "\n";


        if (isMale) {
            out += "Male\n" + this.numUrinals + "\n" ;
        }
        else if (isFemale) {
            out += "Female\n";
        }
        else (isOther) {
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


    }
}