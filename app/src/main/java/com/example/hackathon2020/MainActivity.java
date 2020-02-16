package com.example.hackathon2020;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView BathroomListRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Bathroom> bathrooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      TODO : load array of Bathroom objects
        bathrooms = loadTestBathrooms();
//        bathrooms = loadBathrooms();

        BathroomListRecyclerView = findViewById(R.id.rv_bathroom_list);

        BathroomListRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        BathroomListRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new BathroomsAdapter(bathrooms);
        BathroomListRecyclerView.setAdapter(mAdapter);
    }

    private List<Bathroom> loadTestBathrooms(){
        List<Bathroom> accumulate = new ArrayList<Bathroom>();

        accumulate.add(new Bathroom("CSUS Room", "pic_ms130.jpg","map_ms130.jpg", "MS130", new float[]{51.07996f,-114.12789f}, 1, 1, 1, false, false, true, true, false));
        accumulate.add(new Bathroom("CSUS Room", "pic_ms130.jpg","map_ms130.jpg", "MS130", new float[]{51.07996f,-114.12789f}, 1, 1, 1, false, false, true, true, false));
        accumulate.add(new Bathroom("CSUS Room", "pic_ms130.jpg","map_ms130.jpg", "MS130", new float[]{51.07996f,-114.12789f}, 1, 1, 1, false, false, true, true, false));
        accumulate.add(new Bathroom("CSUS Room", "pic_ms130.jpg","map_ms130.jpg", "MS130", new float[]{51.07996f,-114.12789f}, 1, 1, 1, false, false, true, true, false));
        accumulate.add(new Bathroom("CSUS Room", "pic_ms130.jpg","map_ms130.jpg", "MS130", new float[]{51.07996f,-114.12789f}, 1, 1, 1, false, false, true, true, false));
        accumulate.add(new Bathroom("CSUS Room", "pic_ms130.jpg","map_ms130.jpg", "MS130", new float[]{51.07996f,-114.12789f}, 1, 1, 1, false, false, true, true, false));
        accumulate.add(new Bathroom("CSUS Room", "pic_ms130.jpg","map_ms130.jpg", "MS130", new float[]{51.07996f,-114.12789f}, 1, 1, 1, false, false, true, true, false));
        accumulate.add(new Bathroom("CSUS Room", "pic_ms130.jpg","map_ms130.jpg", "MS130", new float[]{51.07996f,-114.12789f}, 1, 1, 1, false, false, true, true, false));
        accumulate.add(new Bathroom("CSUS Room", "pic_ms130.jpg","map_ms130.jpg", "MS130", new float[]{51.07996f,-114.12789f}, 1, 1, 1, false, false, true, true, false));
        accumulate.add(new Bathroom("CSUS Room", "pic_ms130.jpg","map_ms130.jpg", "MS130", new float[]{51.07996f,-114.12789f}, 1, 1, 1, false, false, true, true, false));

        return accumulate;
    }

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

//    TODO : change view to corresponding bathroom page when clicked

//    TODO : add settings page and link to it

//    TODO : add filter menu and functionality
}
