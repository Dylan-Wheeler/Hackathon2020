package com.example.hackathon2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

        BathroomListRecyclerView = findViewById(R.id.rv_bathroom_list);

        BathroomListRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        BathroomListRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new BathroomsAdapter(bathrooms);
        BathroomListRecyclerView.setAdapter(mAdapter);
    }

//    TODO : change view to corresponding bathroom page when clicked

//    TODO : add settings page and link to it

//    TODO : add filter menu and functionality
}
