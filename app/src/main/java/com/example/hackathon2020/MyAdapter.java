package com.example.hackathon2020;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Bathroom[] mDataset;

    class ViewHolder0 extends RecyclerView.ViewHolder {
        ImageView bathroomImage;

        public ViewHolder0(ImageView imageView){
            super(imageView);

            bathroomImage = imageView;
        }
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {
        TextView bathroomName;

        public ViewHolder1(TextView textView) {
            super(textView);

            bathroomName = textView;
        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
        TextView bathroomDistance;

        public ViewHolder2(TextView textView){
            super(textView);

            bathroomDistance = textView;
        }
    }

    class ViewHolder3 extends RecyclerView.ViewHolder {
        RatingBar bathroomRating;

        public ViewHolder3(RatingBar ratingView) {
            super(ratingView);

            bathroomRating = ratingView;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0: return new ViewHolder0();
            case 2: return new ViewHolder2(...);
             ...
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
