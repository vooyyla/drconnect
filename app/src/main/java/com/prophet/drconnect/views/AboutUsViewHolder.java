package com.prophet.drconnect.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.prophet.drconnect.R;

public class AboutUsViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;

    public AboutUsViewHolder(View view) {
        super(view);
        textView = view.findViewById(R.id.textView);

    }
}
