package com.example.hackathon2020;

import android.widget.ImageView;
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

    class ViewHolder2 extends RecyclerView.ViewHolder {
        TextView bathroomName;

        public ViewHolder2(TextView textView) {
            super(textView);

            bathroomName = textView;
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
