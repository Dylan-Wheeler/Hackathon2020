package com.example.hackathon2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import android.content.Intent;
import android.widget.Toast;

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

//        TODO : make this link to an actual image instead of just the string
        mapImageImageView.setImageDrawable(currentBathroom.getMapID());
    }

    protected void sendSanitationRequest() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"zfrhsngt@sharklasers.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Bathroom Sanitation Request");
        i.putExtra(Intent.EXTRA_TEXT   , currentBathroom.getName() + " needs to be cleaned.");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Context context = getApplicationContext();
            Toast.makeText(context, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
    }
}
