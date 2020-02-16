package com.example.hackathon2020;

import java.io.Serializable;

public class Geolocation implements Serializable {
    private float latitude;
    private float longitude;

    public Geolocation(float lat, float longitude) {
        this.latitude = lat;
        this.longitude = longitude;
    }

    public float getLatitude() {
        return this.latitude;
    }

    public float getLongitude() {
        return this.longitude;
    }

    public float[] toArray(){
        float[] array;

        array = new float[2];

        array[0] = this.getLatitude();
        array[1] = this.getLongitude();

        return array;
    }
}
