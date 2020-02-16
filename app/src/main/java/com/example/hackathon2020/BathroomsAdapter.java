package com.example.hackathon2020;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BathroomsAdapter extends RecyclerView.Adapter<BathroomsAdapter.ViewHolder> {
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public ImageView bathroomImageView;
        public TextView nameTextView;
        public TextView distanceTextView;
        public RatingBar bathroomRatingsBar;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            bathroomImageView = itemView.findViewById(R.id.iv_bathroom_image);
            nameTextView = itemView.findViewById(R.id.tv_bathroom_name);
            distanceTextView = itemView.findViewById(R.id.tv_bathroom_distance);
            bathroomRatingsBar = itemView.findViewById(R.id.rb_bathroom_rating);
        }
    }

    // Store a member variable for the contacts
    private List<Bathroom> mContacts;

    // Pass in the contact array into the constructor
    public BathroomsAdapter(List<Bathroom> bathrooms) {
        mContacts = bathrooms;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public BathroomsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.rv_list_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    /**
     * MODIFIED FROM: https://stackoverflow.com/questions/3694380/calculating-distance-between-two-points-using-latitude-longitude
     *
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     *
     * @returns Distance in Meters
     */
    public static float calculateDistance(double lat1, double lat2, double lon1,
                                  double lon2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        return (float) distance;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(BathroomsAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Bathroom bathroom = mContacts.get(position);

        // Set item views based on your views and data model
        ImageView bathroomImageView = viewHolder.bathroomImageView;
//        TODO : make this give an image instead of just a String of the filename
        bathroomImageView.setImageDrawable(Drawable.createFromPath("pic_ms130.jpg"));

        TextView nameTextView = viewHolder.nameTextView;
        nameTextView.setText(bathroom.getName());

        TextView distanceTextView = viewHolder.distanceTextView;
//        TODO : do some funky math to find the distance from current location
        float distanceLat = bathroom.getLocation()[0];
        float distanceLong = bathroom.getLocation()[1];
//      TODO: get users current location
        float currentLat;
        float currentLong;

        float distance = Math.round(calculateDistance(distanceLat, currentLat, distanceLong, currentLong) * 100) / 100; // Calculates distance then rounds to 2 decimal places.

        distanceTextView.setText(Float.toString(distance));

        RatingBar bathroomRatingBar = viewHolder.bathroomRatingsBar;
        bathroomRatingBar.setRating(bathroom.getRating());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}
