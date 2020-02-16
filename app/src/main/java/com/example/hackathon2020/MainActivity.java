package com.example.hackathon2020;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BathroomsAdapter.OnBathroomListener {

    private RecyclerView BathroomListRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public static float longitude;
    public static float latitude;

    FusedLocationProviderClient mFusedLocationClient;

    private List<Bathroom> bathrooms;
    private List<Bathroom> filteredBathrooms;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

//      TODO : load array of Bathroom objects
        bathrooms = loadTestBathrooms();

        getLastLocation();

        Collections.sort(bathrooms, new CompareBathrooms(new Geolocation(latitude, longitude)));
        Collections.reverse(bathrooms);

        BathroomListRecyclerView = findViewById(R.id.rv_bathroom_list);

        BathroomListRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        BathroomListRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new BathroomsAdapter(bathrooms, this);
        BathroomListRecyclerView.setAdapter(mAdapter);


        //mAdapter.notifyDataSetChanged();
    }

    private List<Bathroom> loadTestBathrooms(){
        List<Bathroom> accumulate = new ArrayList<Bathroom>();
        Bathroom CSUS = new Bathroom("Not a Washroom", "pic_ms130.jpg","map_ms130.jpg", "MS130", new float[]{51.07996f,-114.12789f}, 1, 1, 1, false, false, true, true, false);
        CSUS.addRating(5.0f);
        accumulate.add(CSUS);
        accumulate.add(new Bathroom("ICT 1st Floor North Bathroom", "pic_ict121w.jpg","map_ict121w.jpg", "ICT121W", new float[]{51.08039f,-114.13101f}, 2, 2, 2, true, false, false, true, false));
        accumulate.add(new Bathroom("ENG B NW Basement Bathroom", "pic_enb09w.jpg","map_enb09w.jpg", "ENB09W", new float[]{51.08069f,-114.13146f}, 1, 1, 1, true, false, false, true, false));
        accumulate.add(new Bathroom("Math Science 1st Floor Washroom", "pic_ms126w.jpg","map_ms126w.jpg", "MS126W", new float[]{51.080046f,-114.12821f}, 1, 3, 2, true, false, false, true, false));
        accumulate.add(new Bathroom("MacHall Washroom for All Genders", "pic_mh206w.jpg","map_mh206w.jpg", "MH206W", new float[]{51.07863f,-114.130535f}, 3, 3, 1, false, false, true, false, false));
        accumulate.add(new Bathroom("CSUS Room", "pic_ms130.jpg","map_ms130.jpg", "MS130", new float[]{51.07996f,-114.12789f}, 1, 1, 1, false, false, true, true, false));
        accumulate.add(new Bathroom("CSUS Room", "pic_ms130.jpg","map_ms130.jpg", "MS130", new float[]{51.07996f,-114.12789f}, 1, 1, 1, false, false, true, true, false));
        accumulate.add(new Bathroom("CSUS Room", "pic_ms130.jpg","map_ms130.jpg", "MS130", new float[]{51.07996f,-114.12789f}, 1, 1, 1, false, false, true, true, false));
        accumulate.add(new Bathroom("CSUS Room", "pic_ms130.jpg","map_ms130.jpg", "MS130", new float[]{51.07996f,-114.12789f}, 1, 1, 1, false, false, true, true, false));
        accumulate.add(new Bathroom("CSUS Room", "pic_ms130.jpg","map_ms130.jpg", "MS130", new float[]{51.07996f,-114.12789f}, 1, 1, 1, false, false, true, true, false));

        return accumulate;
    }
/*
    private List<Bathroom> loadBathrooms() {
        List<Bathroom> accumulate = new ArrayList<Bathroom>();
        String path = "bathrooms";
        InputStream in;
        String[] fileNames;
        try {
            fileNames = getAssets().list(path);

            for (String name : fileNames) {
                in = getAssets().open(path + "/" + name);
                ObjectInputStream objIn = new ObjectInputStream(in);
                Bathroom room = (Bathroom) objIn.readObject();
                Log.d("bathrooms", room.getID());
                accumulate.add(room);
                objIn.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return accumulate;
    }
*/
    private boolean checkPermissions(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true;
        }
        return false;
    }

    private void requestPermissions(){
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                1
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // Granted. Start getting the location information
                getLastLocation();
            }
        }
    }

    private boolean isLocationEnabled(){
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        requestingLocationUpdates = true;
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation(){
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                Location location = task.getResult();
                                if (location == null) {
                                    requestNewLocationData();
                                } else {
                                    latitude = (float) location.getLatitude();
                                    longitude = (float) location.getLongitude();
                                    //Log.d("longtitude", String.valueOf(longitude))
                                }
                                mAdapter.notifyDataSetChanged();
                            }
                        }
                );
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData(){

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        //mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );
    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            latitude = (float) mLastLocation.getLatitude();
            longitude = (float) mLastLocation.getLongitude();
        }
    };

//    TODO : change view to corresponding bathroom page when clicked

    public void onBathroomClick(int position) {
        Intent intent = new Intent(this, BathroomActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("BATHROOM_OBJECT", bathrooms.get(position));
        intent.putExtras(bundle);

        Log.d(TAG, position + " currently in queue");

        startActivity(intent);
    }

    private boolean requestingLocationUpdates;
    protected void onResume() {
        super.onResume();
        if (requestingLocationUpdates) {
            getLastLocation();
        }
    }


//    TODO : add settings page and link to it

//    TODO : add filter menu and functionality
}
