package com.prophet.drconnect.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.prophet.drconnect.R;
import com.prophet.drconnect.models.Services;
import com.prophet.drconnect.views.ServiceVIewHolder;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServiceVIewHolder> {
    private List<Services> servicesList;
    Context context;
    public ServicesAdapter(Context context,List<Services> list) {
        this.context = context;
        this.servicesList = list;
    }

    @Override
    public ServiceVIewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_services, parent, false);
        return new ServiceVIewHolder(view);
    }

    @Override
    public void onBindViewHolder(ServiceVIewHolder holder, int position) {
        Services servicesItem = servicesList.get(position);
        holder.name.setText(servicesItem.getClinicName());
        holder.type.setText(servicesItem.getServiceType());

        Glide.with(context).load(servicesItem.getThumbnail()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return servicesList.size();
    }
}
