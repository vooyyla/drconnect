package com.prophet.drconnect.views;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prophet.drconnect.R;

public class DoctorsViewHolder extends RecyclerView.ViewHolder {
    public TextView title, specialty;
    public ImageView thumbnail, overflow;

    public DoctorsViewHolder(View view) {
        super(view);

        title = (TextView) view.findViewById(R.id.title);
        specialty = (TextView) view.findViewById(R.id.specialty);
        thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        overflow = (ImageView) view.findViewById(R.id.overflow);
    }
}
