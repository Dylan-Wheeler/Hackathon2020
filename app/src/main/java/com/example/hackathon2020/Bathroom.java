package com.example.hackathon2020;

import java.io.Serializable;

public class Bathroom implements Serializable {

    private String name;
    private String picture;
    private String map;
    private String ID;
    private Geolocation location;

    private float distance = 0;

    private float totalRatings;
    private int numRatings;

    private int numStalls;
    private int numSinks;
    private int numUrinals;

    private boolean isMale;
    private boolean isFemale;
    private boolean isOther;

    private boolean isWheelchair;
    private boolean isFamily;

    public Bathroom(String name, String pic, String map, String id, float[] loc, int numStalls, int numSinks, int numUrinals,
                    boolean isMale, boolean isFemale, boolean isOther, boolean isWheelchair, boolean isFamily) {
        this.name = name;
        this.picture = pic;
        this.map = map;
        this.ID = id;

        this.location = new Geolocation(loc[0], loc[1]);

        this.totalRatings = 0.0f;
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

    public String getMap() {
        return this.map;
    }

    public String getName() {
        return this.name;
    }

    public String getPicture() {
        return this.picture;
    }

    public String getID() {
        return this.ID;
    }

    public float[] getLocation() {
        return this.getGeolocation().toArray();
    }

    public Geolocation getGeolocation(){
        return this.location;
    }

    public float getRating() {
        float temp = this.totalRatings/this.numRatings;
        return (float) (Math.round(temp * 10) / 10.0);
    }

    public void addRating(float newRating) {
        this.totalRatings += newRating;
        this.numRatings++;
    }

    public float getTotalRatings() {
        return this.totalRatings;
    }

    public int getNumRatings() {
        return this.numRatings;
    }

    public int getNumStalls() {
        return this.numStalls;
    }

    public int getNumSinks() {
        return this.numSinks;
    }

    public int getNumUrinals() {
        return this.numUrinals;
    }

    public boolean isMale() {
        return this.isMale;
    }

    public boolean isFemale() {
        return this.isFemale;
    }

    public boolean isOther() {
        return this.isOther;
    }

    public boolean isWheelchair() {
        return this.isWheelchair;
    }

    public boolean isFamily() {
        return this.isFamily;
    }

    public void setDistance(float dist) {
        this.distance = dist;
    }

    public float getDistance() {
        return this.distance;
    }

    public String getProperties() {
        String out = "Rating: " + this.getRating() + "\n";


        if (this.isMale) {
            out += "Male\n" + this.numUrinals + "\n" ;
        }
        else if (this.isFemale) {
            out += "Female\n";
        }
        else if (this.isOther) {
            out += "Gender Neutral\n" + this.numUrinals + "\n";
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