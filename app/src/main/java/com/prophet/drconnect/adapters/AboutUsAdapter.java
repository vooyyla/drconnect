package com.prophet.drconnect.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prophet.drconnect.R;
import com.prophet.drconnect.models.AboutUs;
import com.prophet.drconnect.views.AboutUsViewHolder;

import java.util.List;

public class AboutUsAdapter extends RecyclerView.Adapter<AboutUsViewHolder> {
    private List<AboutUs> content;
    private Context context;
    public AboutUsAdapter(Context context, List<AboutUs> content) {
        this.context = context;
        this.content = content;
    }

    @NonNull
    @Override
    public AboutUsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_about_us, viewGroup, false);
        return new AboutUsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AboutUsViewHolder holder, int position) {
        AboutUs aboutUs = content.get(position);
        holder.textView.setText(aboutUs.getHeader());
    }

    @Override
    public int getItemCount() {
        return content.size();
    }
}
