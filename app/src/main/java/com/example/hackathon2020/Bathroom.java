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
        String out = "Rating: " + this.getRating() + "/5.0" + "\n";


        if (this.isMale) {
            out += "This is a male washroom.\n" + "Number of Urinals: " + this.numUrinals + "\n" ;
        }
        else if (this.isFemale) {
            out += "This is a female washroom.\n";
        }
        else if (this.isOther) {
            out += "This is a gender neutral washroom.\n" + "Number of Urinals: " + this.numUrinals + "\n";
        }

        out += "Number of Sinks: " + this.numSinks + "\n" + "Number of Stalls: " + this.numStalls + "\n";

        if (isWheelchair && isFamily) {
            out += "Accessible for: wheelchairs and families\n";
        }

        if (isWheelchair) {
            out += "Accessible for: wheelchairs\n";
        }

        if (isFamily) {
            out += "Accessible for: families\n";
        }

        return out;
    }
}