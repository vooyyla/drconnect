package com.prophet.drconnect.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.prophet.drconnect.R;

public class ServiceVIewHolder extends RecyclerView.ViewHolder {

    public TextView name, type;
    public ImageView thumbnail;

    public ServiceVIewHolder(View view) {
        super(view);
        thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        name = view.findViewById(R.id.name);
        type = view.findViewById(R.id.type);
    }


}
