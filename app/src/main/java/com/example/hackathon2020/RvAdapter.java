package com.example.hackathon2020;

import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder> {
    private Bathroom[] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView bathroomImageView;
        public TextView bathroomNameTextView;
        public TextView bathroomDistanceTextView;
        public RatingBar bathroomRatingBar;

        public MyViewHolder(ImageView u, TextView v, TextView w, RatingBar x) {
            super(u, v, w, x);

            bathroomImageView = u;
            bathroomNameTextView = v;
            bathroomDistanceTextView = w;
            bathroomRatingBar = x;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RvAdapter(Bathroom[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RvAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        ImageView u = (ImageView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.iv_bathroom_image, parent, false);

        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tv_bathroom_name, parent, false);

        TextView w = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tv_bathroom_distance, parent, false);

        RatingBar x = (RatingBar) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rb_bathroom_rating, parent, false);

        MyViewHolder vh = new MyViewHolder(u, v, w, x);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(mDataset[position]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
