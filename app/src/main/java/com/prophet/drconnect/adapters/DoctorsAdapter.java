package com.prophet.drconnect.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.prophet.drconnect.R;
import com.prophet.drconnect.models.Doctors;
import com.prophet.drconnect.views.DoctorsViewHolder;

import java.util.List;

public class DoctorsAdapter extends RecyclerView.Adapter<DoctorsViewHolder> {
    private Context mContext;
    private List<Doctors> doctorsList;

    public DoctorsAdapter(Context mContext, List<Doctors> doctorsList) {
        this.mContext = mContext;
        this.doctorsList = doctorsList;
    }

    @Override
    public DoctorsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctors_card, parent, false);
        return new DoctorsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DoctorsViewHolder holder, int position) {
        Doctors doctors = doctorsList.get(position);
        holder.title.setText(doctors.getName());
        holder.qualification.setText(doctors.getQualification());
        holder.specialty.setText(doctors.getSpecialty());

        Glide.with(mContext).load(doctors.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUpMenu(holder.overflow);
            }
        });
    }

    private void showPopUpMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(mContext, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu_doctors, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new CustomMenuClickListener());
        popupMenu.show();
    }

    class CustomMenuClickListener implements PopupMenu.OnMenuItemClickListener {
        public CustomMenuClickListener(){}

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.get_appointment:
                    Toast.makeText(mContext, "Get appointment", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.add_to_favourite:
                    Toast.makeText(mContext, "Add to favourites", Toast.LENGTH_SHORT).show();
                    return true;
                    default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return doctorsList.size();
    }
}
