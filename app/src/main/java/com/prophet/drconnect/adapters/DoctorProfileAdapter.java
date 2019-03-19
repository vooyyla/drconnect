package com.prophet.drconnect.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.prophet.drconnect.R;
import com.prophet.drconnect.models.Doctors;

import java.util.List;

public class DoctorProfileAdapter extends RecyclerView.Adapter<DoctorProfileAdapter.DoctorProfileViewHolder> {

    private Context mContext;
    private List<Doctors> doctorsList;

    public DoctorProfileAdapter(Context mContext, List<Doctors> doctorsList) {
        this.mContext = mContext;
        this.doctorsList = doctorsList;
    }

    @NonNull
    @Override
    public DoctorProfileViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detail_doctor_profile, viewGroup, false);
        return new DoctorProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DoctorProfileViewHolder holder, final int position) {
        Doctors doctors = doctorsList.get(position);
        holder.name.setText(doctors.getName());
        holder.degree.setText(doctors.getDegree());
        holder.specialty.setText(doctors.getSpecialty());
        holder.departments.setText(doctors.getDepartments());
        holder.bio.setText(doctors.getBio());
        Glide.with(mContext).load(doctors.getThumbnail()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return doctorsList.size();
    }

    public class DoctorProfileViewHolder extends RecyclerView.ViewHolder {
        public TextView name, degree, departments, specialty, bio;
        public ImageView thumbnail;
        public DoctorProfileViewHolder(View view) {
            super(view);
           name = view.findViewById(R.id.name);
           degree = view.findViewById(R.id.degree);
           departments = view.findViewById(R.id.department);
           specialty = view.findViewById(R.id.specialty);
           bio = view.findViewById(R.id.bio);
           thumbnail = view.findViewById(R.id.thumbnail);
        }
    }
}
