package com.example.hackathon2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class BathroomActivity extends AppCompatActivity {

    ImageView mapImageImageView;
    TextView bathroomDistanceTextView;
    TextView bathroomPropertiesTextView;
    RatingBar bathroomRatingBar;

    Bathroom currentBathroom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bathroom);

        mapImageImageView = findViewById(R.id.iv_map_image);
        bathroomDistanceTextView = findViewById(R.id.tv_bathroom_distance);
        bathroomPropertiesTextView = findViewById(R.id.tv_bathroom_properties);
        bathroomRatingBar = findViewById(R.id.rb_bathroom_rating);

        mapImageImageView.setImageDrawable(currentBathroom.getMapID());

    }
}
