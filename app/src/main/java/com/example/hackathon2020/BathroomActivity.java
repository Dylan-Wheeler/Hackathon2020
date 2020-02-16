package com.example.hackathon2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
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
    private static final String TAG = "BathroomActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bathroom);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        Bathroom bathroom = (Bathroom) bundle.getSerializable("BATHROOM_OBJECT");
        Log.d(TAG, "Bathroom loaded");

        mapImageImageView = findViewById(R.id.iv_map_image);
        bathroomDistanceTextView = findViewById(R.id.tv_bathroom_distance);
        bathroomPropertiesTextView = findViewById(R.id.tv_bathroom_properties);
        //bathroomRatingBar = findViewById(R.id.rb_bathroom_rating);

        mapImageImageView.setImageResource(R.drawable.eng_bathroom_map);
        Log.d(TAG, "Map Drawn");

        String format;
        if (bathroom.getDistance() < 1000) {
            format = String.format("%3.0f m away", bathroom.getDistance());
        } else {
            format = String.format("%.2f km away", bathroom.getDistance());
        }

        bathroomDistanceTextView.setText(format);
        bathroomPropertiesTextView.setText(bathroom.getProperties());
        //bathroomRatingBar.setRating(bathroom.getRating());
        //mapImageImageView.setImageDrawable(Drawable.createFromPath(bathroom.getPicture()));
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
